FROM openjdk:11-jre-slim
RUN mkdir /app
WORKDIR /app

ADD ./target/product-svc-1.0.0.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]