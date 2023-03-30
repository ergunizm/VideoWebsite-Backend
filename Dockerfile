FROM openjdk:19
EXPOSE 8080
ADD target/springboot-videos.jar springboot-videos.jar
ENTRYPOINT ["java","-jar","/springboot-videos.jar"]