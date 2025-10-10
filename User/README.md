# to Run Spring project all services need to Run to Fetch Data 
# Run ServiceRegistry "Eureka:

     browser : http://localhost:8761/

* Eureka client Add User pom.xml:   
* set application.yml

      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          <version>4.3.0</version>
      </dependency>

# Kafka Run with Lenses:

    Run Docker File : "docker compose up -d"

# Kafka Lenses  Running port :

       localhost://9991

* User-service {Producer} , Policy-service {Consumer}
      
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


* 4th API Calls Policy service internally
* entity = PolicyWithUserDTO
* service = UserService  method = getUserWithPolicies
* Resttemplate write in "PolicyDataCLient"
* Returns all policies of the user