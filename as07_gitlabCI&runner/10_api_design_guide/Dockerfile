FROM harbor-k8s.wistron.com/witlab/openjdk:8-jdk-alpine

ARG PROJECT_NAME=${PROJECT_NAME}
ARG PROJECT_VERSION=${PROJECT_VERSION}

COPY target/${PROJECT_NAME}-${PROJECT_VERSION}.jar /app/app.jar
COPY src/main/resources/application.properties /app/application.properties

ENTRYPOINT ["java","-jar","/app/app.jar"]
