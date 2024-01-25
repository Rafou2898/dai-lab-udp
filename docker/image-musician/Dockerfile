FROM openjdk:21

WORKDIR /app

# TODO find how to use target directory and if we have to do a README
COPY musician.jar .

EXPOSE 9904

ENTRYPOINT ["java", "-jar", "musician.jar"]