FROM openjdk:12

WORKDIR /apps
#VOLUME [ "./build/libs/:/apps" ]
ENV jarver ${env.BUILD_NUMBER}
COPY ./build/libs/test-gradle-${jarver}-spring.jar /apps/test-gradle-${jarver}-spring.jar
ENTRYPOINT [ "java" ]
CMD [ "-jar", "-Dserver.port=9090" ,"/apps/test-gradle-${jarver}-spring.jar" ]
EXPOSE 9090