# Etapa 1: build da aplicação com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem final
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/api_imoveis-0.0.1-SNAPSHOT.jar app.jar

# Porta exposta
EXPOSE 8090

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
