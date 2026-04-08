FROM eclipse-temurin:25
LABEL authors="Thant Phyo Maung"
COPY ./target/classes/org /tmp/org
WORKDIR /tmp
ENTRYPOINT ["java", "org.example.Main"]