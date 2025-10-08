# to Run Spring project all services need to Run to Fetch Data
*      RUN Policy Port : 8083
# Run ServiceRegistry "Eureka:
     
      browser : http://localhost:8761/

* Eureka client Add User pom.xml:
* set application.yml

      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          <version>4.3.0</version>
      </dependency>

* Run All Services :

       { User, policy,  DrivingHistory, Service Registry , Claim Module, }

* fign client use to call Driving-History service & other service

       @feignClient(name="user-service", url="http://localhost/8081")


1. Create a Policy

        POST : http://localhost:8083/policies/save

        Body (JSON):

        {
           "userid": 1,
           "licenseNo": "MH12546",
            "premium_amount": 5000,
            "type": "Car",
             "startDate": "2025-09-29",
              "endDate": "2026-09-29"
            }


*  userid must match a user in User service

2. Get All Policies

       GET : http://localhost:8083/policies

3. Get Policy by ID

       GET : http://localhost:8083/policies/10

4. Get Policies by User ID

       GET : http://localhost:8083/policies/user/1

5. generate Dummy Policies & price for user
          
       Get : http://localhost:8083/policies/dummy/MH12546/25

* Returns list of policies for userID 1

* Used by User service RestTemplate call


# Dummy Policy

1. save { creatye Policy for user }

         Post: http://localhost:8083/policies/dummy/save

2.Get policy by using license ( price of policy based on driving History "age")

         Get: http://localhost:8083/policies/dummy/generate/MH123456

3.All dummy Policy for user
         
         Get: http://localhost:8083/policies/dummy/all