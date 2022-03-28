FROM amazoncorretto:11

MAINTAINER Vitor Ferreira <vrfvitor@hotmail.com>

RUN mkdir /opt/kn

COPY target/knwallets.jar /opt/kn/knwallets.jar

EXPOSE 8080

CMD ["sh", "-c", "java -jar /opt/kn/knwallets.jar"]