# Step 1: Build app
FROM maven:3.9.9-amazoncorretto-21-al2023 as build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Step 2: Run app
FROM amazoncorretto:21.0.7-alpine3.21

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]




