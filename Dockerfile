FROM eclipse-temurin:21
COPY target/*.jar dailydo-api.jar
ENTRYPOINT [ "java", "-jar", "dailydo-api.jar" ]