FROM openjdk:21-slim
ENV _JAVA_OPTIONS="-Xms128m -Xmx1024m -Dfile.encoding=utf-8"
ENV TZ=Europe/Minsk

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ARG APP_PATH="/app"
ARG JAR_FILE="target/*-jar-with-dependencies.jar"
COPY ${JAR_FILE} ${APP_PATH}/api-service.jar
WORKDIR "${APP_PATH}"

EXPOSE 8000
ENTRYPOINT ["java","-jar", "api-service.jar"]