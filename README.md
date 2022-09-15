# Job Application

* Sample application built using Spring Boot and JWT Authentication
* Java - 1.8
* Swagger - http://localhost:9191/swagger-ui.html

## To Start the application, execute the following command from command prompt

`java -jar jobapplication-0.0.1-SNAPSHOT.jar`

The application will start at default port - **9191**

## To Start the application on a specific port, execute the following command from command prompt

`java -jar -Dserver.port=8989 jobapplication-0.0.1-SNAPSHOT.jar`

The application will start at default port - **8989**

## Docker image

Run the following command to build the image and tags it as `springio/job-application`.

`mvn package`

`docker build -t springio/job-application`

Run the container using the image 

`docker run -d -p <application-port>:<expose-port> springio/job-application`
