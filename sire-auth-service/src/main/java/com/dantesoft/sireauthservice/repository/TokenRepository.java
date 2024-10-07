package com.dantesoft.sireauthservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dantesoft.sireauthservice.entity.Token;

public interface TokenRepository extends JpaRepository<Token, UUID> {

	@Query("""
			select t from Token t inner join User u on t.user.id = u.id
			where t.user.id = :userUuid and t.loggedOut = false
			""")
	List<Token> findAllTokensByUser(UUID userUuid);

	Optional<Token> findByToken(String token);
}
