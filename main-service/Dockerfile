FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY ./impl/build/libs/*.jar /app/SkillShare.jar

ENTRYPOINT ["java", "-jar", "SkillShare.jar"]
