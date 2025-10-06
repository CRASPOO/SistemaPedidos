FROM openjdk:17-jdk
WORKDIR /app
COPY target/cardapio-0.0.1-SNAPSHOT.jar /app/app.jar
RUN npm install
COPY . .
EXPOSE 8080
CMD ["java", "-jar", "app/app.jar"]