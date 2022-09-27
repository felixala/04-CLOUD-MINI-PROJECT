# 04-CLOUD-MINI-PROJECT
## This is a Cloud mini project using Microservice Architecture.

![Microservice](https://github.com/felixala/04-CLOUD-MINI-PROJECT/tree/main/SCREENSHOTS/MicroserviceArchitecture.png)


## Implementation
### 1) Develop Service Registry Application (Eureka Server)
    http://localhost:8761/

### 2) Develop Spring Boot Admin Server (Admin Server)
    http://localhost:1111/

### 3) Download Zipkin Server
a) Link to donwload: https://zipkin.io/pages/quickstart.html

b) Run Zipkin Server jar from command line

    java -jar <jar-file-name>

c) Access to Zipkin Server

    http://localhost:9411/

### 4) Develop GREET-API (producer)

a) Create Spring Boot application with next dependencies:

		- eureka-discovery-client
		- starter-web
		- devtools
		- actuator
		- sleuth
		- zipkin
		- admin-client

b) Configure start class with @EnableDiscoveryClient annotation.

c) Create RestController class.

d) Configure below properties in application.yml file

    server:
      port: 9090
    spring:
      application:
        name: GREET-API
      boot:
        admin:
          client:
            url: http://localhost:1111/
    eureka:
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka
    management:
      endpoints:
        web:
          exposure:
            include: '*'

### 5) Develop WELCOME-API

a) Create Spring Boot application with next dependencies

			- web-starter
			- devtools
			- eureka-discovery-client
			- fegin-client
			- admin-client
			- zipkin-client
			- sleuth
			- actuator

b) Configure start class with @EnableDiscoveryClient & @EnableFeignClients annotations.

c) Create FeignClient to access GREET-API


    @FeignClient(name = "GREET-API")
    public interface GreetApiClient {
        @GetMapping("/greet")
        public String invokeGreetApi();
    }

d) Create RestController

e) Configure below properties in application.yml file.

    server:
      port: 9091
    spring:
      application:
        name: WELCOME-API
      boot:
        admin:
          client:
            url: http://localhost:8080/
    eureka:
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka
    management:
      endpoints:
        web:
          exposure:
            include: '*'

### 6) Develop API-GATEWAY
a) Configure below properties in application.yml file.

    spring:
      cloud:
        gateway:
          discovery.locator:
            enabled: true
            lowerCaseServiceId: true
          routes:
          - id: welcome-api
            uri: lb://WELCOME-API
            predicates:
            - Path=/welcome
          - id: greet-api
            uri: lb://GREET-API
            predicates:
            - Path=/greet
      application:
        name: CLOUD-API-GATEWAY
    server:
      port: 3333

b) Run application

    http://localhost:3333/welcome
    http://localhost:3333/greet

