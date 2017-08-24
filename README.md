# elsie-dee-search
Elsie-Dee Search microservice is used to perform search and documents / indexes creation with Elastic.

# Dependencies

* Configuration Service
  * This microservice depends on the Configuration Service in order to retrieve its settings. It means that before starting this server,
    please make sure that the one it depends on is already running.
  * You can find out how to run the Configuration Service here: [Configuration Service](https://github.com/ekholabs/configuration-service)
* Eureka Service
  * As a second note, this microservice also depends on the Eureka Service in order to register for service discovery. However,
    the Eureka Service does not need to be running before this one can be started.
  * You can find out how to run the Configuration Service here: [Eureka Service](https://github.com/ekholabs/eureka-service)
* elsie-dee-elastic
  * The ```elsie-dee-elastic``` image is included in the [Elsie-Dee Microservices](https://github.com/ekholabs/elsie-dee-microservices) Docker Compose file.

In a dependency order priority, the Eureka Service should be started before everything else. The second in the list must be the Configuration Service.

# Pulling the Docker Image

* ```docker pull ekholabs/elsie-dee-search```

# Running the Docker Container

* ```docker run -p 8087:8087 --link eureka-service --link configuration-service --link elsie-dee-elastic --name=elsie-dee-search ekholabs/elsie-dee-search```

Elsie-Dee Search will run on the background. To check details about the container, execute the following:

* ```docker ps```

For logs:

* ```docker logs [container_id]```

# Actuator Endpoints

Once the application is running, the user/developer can find health status and metrics via the following endpoints:

* http://localhost/elsie-dee-search/health
* http://localhost/elsie-dee-search/metrics
* http://localhost/elsie-dee-search/env

# Application Endpoints

[wip]