FROM eclipse-temurin:17
RUN mkdir /opt/app
COPY target/*.jar /opt/app
CMD ["java", "-jar", "/opt/app/.jar"]