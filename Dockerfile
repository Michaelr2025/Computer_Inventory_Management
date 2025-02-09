# שלב הבנייה
FROM maven:3.9.9-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# שלב הריצה
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/Computer_Inventory_Management.jar .
CMD ["java", "-jar", "Computer_Inventory_Management.jar"]
