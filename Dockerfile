# Use a imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim AS build

# Instala o Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml e outros arquivos de configuração do Maven
COPY pom.xml .

# Copia o código-fonte da aplicação para o contêiner
COPY src ./src

# Compila o projeto e gera o arquivo JAR
RUN mvn clean package -DskipTests

# Use a imagem base do OpenJDK 21 para o estágio final
FROM openjdk:21-jdk-slim

# Copia o arquivo JAR gerado para o diretório de trabalho
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que a aplicação irá rodar
EXPOSE 80

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]