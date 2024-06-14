FROM maven:3.8.6-openjdk-18 as build
LABEL author="André Gomes"
LABEL version="0.0.1-SNAPSHOT"

ENV APP_HOME /usr/app
ENV ARTIFACT_NAME shop-0.0.1-SNAPSHOT.jar

COPY ./ $APP_HOME

WORKDIR $APP_HOME

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
ENV APP_HOME /usr/app
ENV ARTIFACT_NAME shop-0.0.1-SNAPSHOT.jar

ENV spring.datasource.url jdbc:mysql://shopdb:3306/shops?autoreconnect=true&allowPublicKeyRetrieval=true&useSSL=false
ENV spring.datasource.username admin
ENV spring.datasource.password @Dmin123
ENV logging.level.org.springframework.web INFO


VOLUME /tmp

ARG JAR_FILE=target/$ARTIFACT_NAME



COPY --from=build $APP_HOME/target/$ARTIFACT_NAME .
EXPOSE 8080
ENTRYPOINT java -jar $ARTIFACT_NAME