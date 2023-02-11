FROM openjdk:12

WORKDIR /apps
VOLUME [ "./build/libs/:/apps" ]
ENV jarver '1.0'
#COPY ./build/libs/pipebuild-${jarver}.jar /pipebuild.jar
ENTRYPOINT [ "java" ]
CMD [ "-jar", "-Dserver.port=9090" ,"/apps/pipebuild-1.0.jar" ]
EXPOSE 9090