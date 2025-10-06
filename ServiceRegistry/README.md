# service registry use for  "Eureka"

*     RUN User Service Port : 8081

* Run Eureka application in browser
 
      url: http://localhost:8761/

* This is showinmg microservices endpoint(application) Running or not
               
      "UP" = running service

# Step to build:
* dependancy :  

      <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
       </dependency>

* set application.yml : 
* in " main "method use Annotation : @EnableEurekaServer

# Register eureka in other modules 
            { user, Policy, DrivingHistory, claim }

* ADD Eureka Client Dependancy
* user service & Same dependancy in other services 
* use same service port in application.yml file:
  
* polm.xml :
<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

# set applicaton-eureka.yml

server:
port:8083     // service module port keep same in yml

spring:
application:
name:USER_SERVICE

    Eureka:
      client:
        service-url:
          defaultZone: http://localhost:8761/eureka/