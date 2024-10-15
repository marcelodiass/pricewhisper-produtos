# Use a imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim

# Instala o Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml e outros arquivos de configuração do Maven
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn dependency:go-offline

# Copia o código-fonte da aplicação para o contêiner
COPY src ./src

# Compila o projeto e gera o arquivo JAR
RUN mvn clean package

# Copia o arquivo JAR gerado para o diretório de trabalho
COPY target/pricewhisper-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação irá rodar
EXPOSE 80

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]