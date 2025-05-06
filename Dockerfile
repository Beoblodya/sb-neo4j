FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN ./mvnw clear package -DskipTests
EXPOSE 8080
CMD ["java", "-jar","sb-neo4j"]