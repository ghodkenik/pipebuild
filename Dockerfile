FROM openjdk:12

ARG BUILD_NUMBER
ENV jarver=$BUILD_NUMBER
WORKDIR /apps
#VOLUME [ "./build/libs/:/apps" ]
COPY ./build/libs/test-gradle-${jarver}-spring.jar /apps/test-gradle-${jarver}-spring.jar
ENTRYPOINT [ "java" ]
CMD [ "-jar", "-Dserver.port=9090" ,"/apps/test-gradle-"${jarver}"-spring.jar" ]
EXPOSE 9090