FROM openjdk:17
WORKDIR /app
COPY target/Computer_Inventory_Management.jar .
CMD ["java", "-jar", "Computer_Inventory_Management.jar"]
