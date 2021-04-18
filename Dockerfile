FROM openjdk:8
ARG JAR_FILE=target/Yemek-Tarifi-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} Yemek-Tarifi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "Yemek-Tarifi.jar"]