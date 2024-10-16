package com.dantesoft.siregatewayservice.filter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dantesoft.siregatewayservice.dto.UserDto;
import com.dantesoft.siregatewayservice.exception.InvalidAuthException;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter
    extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

  public AuthenticationFilter(WebClient.Builder webClientBuilder) {
    super(Config.class);
    this.webClientBuilder = webClientBuilder;
  }

  public static class Config {}

  private final WebClient.Builder webClientBuilder;

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      HttpHeaders headers = exchange.getRequest().getHeaders();

      Optional<String> optionalAuthHeader = headers.getOrEmpty(HttpHeaders.AUTHORIZATION).stream().findFirst();

      String authHeader = optionalAuthHeader.filter(header -> !header.isEmpty())
          .orElseThrow(() -> new InvalidAuthException("Missing Authorization header"));

      String token = this.extractBearerToken(authHeader);

      return webClientBuilder.build()
          .post()
          .uri("http://auth-service/api/v1/auth/validate/{token}", token)
          .retrieve()
          .bodyToMono(UserDto.class).<ServerWebExchange>handle((userDto, sink) -> {
            
            exchange
                .getRequest()
                .mutate()
                .header("x-auth-user-id", String.valueOf(userDto.getId()))
                .build();
                sink.next(exchange);
          }).flatMap(chain::filter);
    };
  };

  /**
   * Validates the bearer token structure and return the value of header
   * 
   */
  private String extractBearerToken(String authHeader) {
    String[] parts = authHeader.split(" ");
    if (parts.length != 2 || !"Bearer".equals(parts[0])) {
      throw new InvalidAuthException("Invalid Authorization structure");
    }
    return parts[1];
  }
}
