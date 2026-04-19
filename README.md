# Steps to Access Apis. Check "User-Service: Readme.md File for Apis endpoints"
    1. Run All Service steps: 
       1. ServiceRegistry
       2. Api Gateway
       3. Auth service: { use for Authentification Register/Login User 
          then access User apis. "Some User Service apis is secure if direct access then 403 error get". "show work without auth in "User service" like all
       4. Run Service : User, policy, DrivingHistory, Claim_Module.
       
# Postman: 
    1. We cant Access All Apis Without Auth: some User Service Apis accessible without Auth
    2. Generate JWT token to access apis user service Using Api-Gateway url: 8085.
        
----------------------------------------------------------------------------------------------------------------------------

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
                 Add Login Token => Postman -> Authentification -> Bearer Tocken -> Add login token to access apis
                   
                      * GET : http://localhost:8085/users/all
                      * Delete : http://localhost:8085/users/6




# 1) Steps To test Apis using Auth Service
# From postman: Access Apis step, Login / Signup

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
     *   1. User Service Apis accessible only using API-Gateway port: 8085 Using -> user generated login Tocken above 3rd step.

      *   2. if try to call direct user service without Gateway then get Error : "403 forbidden"

    4. call api-gateway 8085 ->
         postman -> http://localhost:8085/users/all -> other apis call same

      Authorization ->
                          Bearer tocken { add login generated jwt tocken }
    5. access Api { without token 401 Error unthorised } 
    6. call User Service Api Using Api gateway 8085 -> Access APis based on Role 



# Access Apis only using Api gateway JWT token || not user service directly 8081

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

-----------------------------------------------------------------------------------------------

# Security Implement in 
✅  auth-Service -> SignUp / Login Api
✅  Api-Gateway Service
✅  User-Service

✅ Api gateway  -> Route 7 & Authenticate user and return jwt token to access api {use token when call Apis}

    1. pom.xml : spring Security dependancy added
     2. conmfig : securityConfig file added
    3. UserController : each method add @PreAuthorize. if not use annotation -> all user access not annoted method
    4. // USER + ADMIN Access
          @PreAuthorize("hasAnyRole('USER','ADMIN')")
    5. // ADMIN Access only
          @PreAuthorize("hasRole('ADMIN')")


---------------------------------------------------------------------------------------------------------------------------
# This is For monitor app
# write this prometheus.yml file in file explore that collect application matrix in time serise form.
# install grafana & Prometheus server on our pc. 

    global:
    scrape_interval: 15s
    evaluation_interval: 15s
    
    alerting:
    alertmanagers:
    - static_configs:
      - targets: []
    
    rule_files: []
    
    scrape_configs:
    - job_name: "prometheus"
      static_configs:
        - targets: ["localhost:9090"]
          labels:
          app: "prometheus"
    
      - job_name: "springboot-app"
        metrics_path: "/actuator/prometheus"
        static_configs:
          - targets: ["localhost:8080"]
            labels:
            app: "prometheus_grafana"
    
      - job_name: 'user-service'
        metrics_path: '/actuator/prometheus'
        static_configs:
          - targets: ['localhost:8081']
    
      - job_name: 'drivinghistory-service'
        metrics_path: '/actuator/prometheus'
        static_configs:
          - targets: ['localhost:8082']
    
      - job_name: 'policy-service'
        metrics_path: '/actuator/prometheus'
        static_configs:
          - targets: ['localhost:8083']
    
      - job_name: 'claim-service'
        metrics_path: '/actuator/prometheus'
        static_configs:
          - targets: ['localhost:8084']
    
      - job_name: 'API-Gateway'
        metrics_path: '/actuator/prometheus'
        static_configs:
          - targets: ['localhost:8085']
    
      - job_name: 'service-registry'
        metrics_path: '/actuator/prometheus'
        static_configs:
          - targets: ['localhost:8761']
