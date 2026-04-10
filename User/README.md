# to Run Spring project all services need to Run to Fetch Data 
# Run ServiceRegistry "Eureka:

     browser : http://localhost:8761

* Eureka client Add User pom.xml:   
* set application.yml

      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          <version>4.3.0</version>
      </dependency>

# Kafka Run with Lenses:

    Run Docker File : "docker compose up -d"

# Kafka Lenses  Running port : 9991

       localhost://9991
# Prometheus : 

       http:localhost://9090
* Prometheus Matrix EndPoints

      http:localhost://8080/actuator/prometheus

* Prometheu multiple Queries{Application Health}

      http:localhost://8080/actuator/beans

# Grafana :

     http:localhost://3000

# access APIs Based on role : @PreAuthorize("hasRole('ADMIN')") -> controller class
       Postman: 
        STEP 1: LOGIN & GET JWT TOKEN
                   
                    * POST http://localhost:8085/auth/login
             Role + user find =>
                    * Get  http://localhost:8085/test/profile    

            * ADMIN : username:"rutwik" , Password:"rutwik" = >  || ADMIN + USER  Access
               * ADMIN = All APIs + All user , delete user :
                     
            * USER : username: "ram", Pass : "123" || user: "a" , pass: "a". ||  USER Access
                * USER = All APIs { ❌all user ❌ delete user }
      
       STEP 2 : User Apis call by API Gateway
                 Add Login Tocken => Postman -> Authentification -> Bearer Tocken -> Add login tocken to access apis
                   
                      * GET : http://localhost:8085/users/all
                      * Delete : http://localhost:8085/users/6




# 1) postman : Access Apis step, Login / Signup 

    1. Run Auth_Service -> postman *Auth=  login / signup
    2. signup : create new user
           post: http://localhost:8085/auth/signup
          {
             "username": "ram",   
              "password": "123",
              "role":"ROLE_USER"  // "ROLE_ADMIN"
            }

     * ADMIN : username:"rutwik" , Password:"rutwik" = >  || ADMIN + USER  Access
     * USER : username: "ram", Pass : "123" || user: "a" , pass: "a". ||  USER Access

    3. login : post : http://localhost:8085/auth/login
       Role ADMIN : 

       User :
         {
           "username": "shyam",  
           "password":"123"       
          }
       
       tocken generated ex.:
          eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOi3NzM0OTB9.cCMTAes_A8rE6i5GEJN3nQknyi6C6d_A1dDKBNSsZw8

     

# 2) postman -> call Apis :
Note : 
   *   1. User Service Apis accessible only using API-Gateway 8085 Using -> user generated login Tocken 3rd step.

   *   2. if try to call direct user service without Gateway then get Error : "403 forbidden"
    
    4. call api-gateway 8085 ->
postman -> http://localhost:8085/users/all -> other apis call same
        
      Authorization ->
                          Bearer tocken { add login generated jwt tocan }
    5. access Api { without tocan 401 Error unthorised } 
    6. call User Service Api Using Api gateway 8085 -> Access APis based in ROle 
    

       
# Access Apis only using Api gateway JWT tocken || not user service directly 8081
 
   1. cant direct access  : Get : http://localhost:8081/users/all  error 403
   2. using Tocken 8085 : Get :   http://localhost:8085/users/all
   3. API-Gateway uri :  http://localhost:8085/users/.....

              ex: save, all, 1, policy/1, newPolicies/Mh278648/35


     ✔ 8085 works → GOOD

     ✔ 8081 gives 403 → PERFECT

     ✔ Your architecture is CORRECT
     
# User-service {Producer}, Policy-service {Consumer}
      
       save data using: localhost://8081/user/kafkaproducer
* Run All Services :

       { User, policy,  DrivingHistory, Service Registry , Claim Module, }

Create Save User:

    POST : http://localhost:8081/users

    {
       "fullName": "John Doe",
       "email": "john@example.com",
       "phone": "9876543210",
        "licenseNo": "MH123456"
     }



1. Create a User

       POST http://localhost:8081/api/users/save

2. Get All Users

       GET http://localhost:8081/api/users/all

3. Get User by ID

       GET http://localhost:8081/api/users/1

4. Get Policies using Userid (via RestTemplate)

        GET http://localhost:8081/api/users/policy/1

5. Dummy policies for user to purchase show price by proveding licenseNo and age

       GET : http://localhost:8081/api/users/newPolicies/Mh278648/35

6. API-Gateway uri

         http://localhost:8085/users/.....
                   ex: save, all, 1, policy/1, newPolicies/Mh278648/35

* 4th API Calls Policy service internally
* entity = PolicyWithUserDTO
* service = UserService  method = getUserWithPolicies
* Resttemplate write in "PolicyDataCLient"
* Returns all policies of the user

# Security Impliment
✅ auth-Service -> SignUp / Login Api

✅ Api gateway  -> Route 7 & Authenticate user and return jwt tocan to access api {use tocken when call Apis}

    1. pom.xml : spring Security dependancy added
     2. conmfig : securityConfig file added
    3. UserController : each method add @PreAuthorize. if not use annotation -> all user access not annoted method
    4. // USER + ADMIN Access
          @PreAuthorize("hasAnyRole('USER','ADMIN')")
    5. // ADMIN Access only
          @PreAuthorize("hasRole('ADMIN')")
