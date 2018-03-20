FROM java:8
MAINTAINER JonBall
WORKDIR /home
EXPOSE 8080
ADD notes-1.0-SNAPSHOT.jar /home/notes-1.0-SNAPSHOT.jar
CMD ["java","-jar","notes-1.0-SNAPSHOT.jar"]