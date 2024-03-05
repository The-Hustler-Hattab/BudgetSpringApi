FROM openjdk:17-oracle

WORKDIR /budget


COPY maven/*.jar  /budget/budget.jar

EXPOSE 8070




ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "budget.jar"]