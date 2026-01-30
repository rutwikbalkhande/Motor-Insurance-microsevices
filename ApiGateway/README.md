# Add Dependancy

        1. Cloud gateway : 
                <artifactId> spring-cloud-starter-gateway</artifactId>

        2.Spring webflux
             <artifactId>spring-boot-starter-webflux</artifactId>

          webflux required : 
                     Cloud gateway build on Spring webflux. not spring MVC

# Set Application.yml file 
Add all service name, uri, path repeat this again & again for all service same only change id, uri , path .

* Path=/users/** => 

  * in user service we create interface for " FeignClient "
  * There use Service name instead op port
  * create method @Getmapping ("/user/{id}")  
  * path user/** accept all data after user url. 


     routes:
      - id: user-service
       uri: http://localhost:8081
       predicates:
     - Path=/users/**

 OR 
 
we can use generally uri- "service name" instead of "localhost:8080"

     - id: user-service
       uri: lb://USER-SERVICE
       predicates:
       - Path=/users/**

   
# Authentication impliment Security folder

    api-gateway
    ├── security
    │   ├── JwtUtil               (VALIDATE token)
    │   └── JwtAuthenticationFilter
    ├── config
    │   └── GatewayRoutes
    ├── application.yml

# 🔁 REQUEST FLOW (READ THIS TWICE)
    Client
     ↓  (JWT in header)
    API Gateway
     ↓  (validate token)
    User Service


If token is ❌ invalid → request stops at gateway
If token is ✅ valid → request goes forward