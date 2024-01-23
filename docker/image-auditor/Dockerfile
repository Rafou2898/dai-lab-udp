FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# TODO find how to use target directory and if we have to do a README
COPY auditor.jar .

EXPOSE 9904
EXPOSE 2205

CMD ["java", "-jar", "auditor.jar"]