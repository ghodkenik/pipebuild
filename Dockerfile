FROM openjdk:12

WORKDIR /apps
#VOLUME [ "./build/libs/:/apps" ]
ENV jarver $BUILD_NUMBER
COPY ./build/libs/test-gradle-${jarver}.jar /apps/test-gradle-${jarver}.jar
ENTRYPOINT [ "java" ]
CMD [ "-jar", "-Dserver.port=9090" ,"/apps/test-gradle-${jarver}.jar" ]
EXPOSE 9090