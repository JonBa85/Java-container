# Java-container

**Solution by Jon Ball**

The task is to run a Java application using docker with CouchDB as its backend on port 5984, my solution below enables the application to run as a container along with CouchDB on the local machine.

### **Software used for the task**

- Java jdk-8u161-windows-x64
- Git for Windows
- Docker for Windows

### **Step 1**

Use docker to pull apache couchdb image.
```
docker pull apache/couchdb:1.7.1
```
### **Step 2**

Run the couchdb image as a container, expose it on port 5984 and name the container "couchdb".
```
docker run -d -p 5984:5984 --name couchdb apache/couchdb:1.7.1
```
### **Step 3**

The Java application connects to the database using http://couchdb:5984 so a local host entry is required for couchdb resolution.

Edit C:\Windows\System32\drivers\etc\hosts and add
```
"DOCKER NAT IP"	couchdb
```
