# # Start with a base image containing Java runtime
# FROM amazoncorretto:17
#
# EXPOSE 8080
#
# # Add maintainer info
# LABEL maintainer="Alina B <alinalinal@gmail.com>"
#
# # The application's jar file - defines a JAR_FILE variable set by dockerfile-maven-plugin
# ARG JAR_FILE=target/*.jar
#
# # Add the application's jar to the container
# COPY ${JAR_FILE} app.jar
#
# # Execute the application
# ENTRYPOINT exec java \
#     -Dspring.profiles.active=$SPRING_PROFILE \
#     -Duser.timezone=Asia/Tbilisi \
#     -jar app.jar

FROM amazoncorretto:17 AS build
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# ---------------------------------------------------------------------------------------------------------

FROM amazoncorretto:17
WORKDIR application
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./
ENTRYPOINT ["java", "-Duser.timezone=Asia/Tbilisi", "-Dspring.profiles.active=${SPRING_PROFILE}", "org.springframework.boot.loader.launch.JarLauncher"]