# **Java-container**

**Solution by Jon Ball**

The task is to run a Java application using docker with CouchDB as its backend on port 5984, my solution below enables the application to run as a container along with CouchDB on the local machine.

**Software used for the task**

    Java jdk-8u161-windows-x64
    Git for Windows
    Docker for Windows

**Step 1**

Use docker to pull apache couchdb image.
```
docker pull apache/couchdb:1.7.1
```
**Step 2**

Run the couchdb image as a container, expose it on port 5984 and name the container "couchdb".
```
docker run -d -p 5984:5984 --name couchdb apache/couchdb:1.7.1
```
**Step 3**

The Java application connects to the database using http://couchdb:5984 so a local host entry is required for couchdb resolution.

Edit C:\Windows\System32\drivers\etc\hosts and add an entry for couchdb to resolve to the Docker IP.
```
"DOCKER NAT IP"	couchdb
```
**Step 4**

Build the Java application. This will build the application and put the .jar files in <projecct path>\build\lib
```
git clone https://github.com/MyLifeDigital/mld-devops-test.git
cd <project path>
gradlew.bat clean build
```
**Step 5**

Create a Dockerfile in the <projecct path>\build\lib folder to build the docker image.
```
FROM java:8
MAINTAINER JonBall
WORKDIR /home
EXPOSE 8080
ADD notes-1.0-SNAPSHOT.jar /home/notes-1.0-SNAPSHOT.jar
CMD ["java","-jar","notes-1.0-SNAPSHOT.jar"]
```

**step 6**

Build an image using docker.
```
cd <project path>/build/lib
docker build -t mld .
```

**step 7**

Run a container from the image and link it to the running CouchDB container.
```
docker run -d --name mld1 --p 8080:8080 --link couchdb mld
```
**step 8**

Confirm the application is running by browsing to http://"DOCKER NAT IP":8080/swagger-ui.html and testing the application.
    
   
