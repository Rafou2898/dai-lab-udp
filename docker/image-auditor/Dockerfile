FROM openjdk:21

WORKDIR /app

# TODO find how to use target directory and if we have to do a README
COPY target/image-auditor-1.0-jar-with-dependencies.jar/ .

EXPOSE 9904
EXPOSE 2205

CMD ["java", "-jar", "image-auditor-1.0-jar-with-dependencies.jar"]