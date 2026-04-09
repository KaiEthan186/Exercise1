FROM eclipse-temurin:25
LABEL authors="Thant Phyo Maung"
COPY ./target/testproject.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar", "testproject.jar", "yes"]