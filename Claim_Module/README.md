# to Run Spring project all services need to Run to Fetch Data
* Claim_module Port : 8084
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

# APIs TO Test Port : 8084

1️⃣ Save a Claim

    POST URL:

    http://localhost:8084/claims/save


Body (JSON):

     {
       "licenseNo": "MH123458",
        "reason": "Accident",
        "incidentDate": "2025-10-01"
          "claimAmount": 400000.0
       }


Notes:

* claimId, userId, policyNo, claimAmount, and status will be automatically set from Policy service.

* Policy service must have a policy with licenseNo = MH123458.

Response (JSON):

      {
           "claimId": 1696357260000,
           "userId": 3,
           " policyNo": "POL-1234",
           "licenseNo": "MH123458",
           "claimAmount": 5000.0,
            "reason": "Accident",
           "incidentDate": "2025-10-01",
           "status": "PENDING"
        }

2️⃣ Get Claim by ID

    GET URL:

        http://localhost:8084/claims/6


Response:

        {
          "claimId": 1696357260000,
           "userId": 3,
           "policyNo": "POL-1234",
           "licenseNo": "MH123458",
           "claimAmount": 5000.0,
            "reason": "Accident",
            "incidentDate": "2025-10-01",
            "status": "PENDING"
          } 

3️⃣ Get Claim by License Number

    GET URL:

          http://localhost:8084/claims/license/MH123457


         Response:
      {
          "claimId": 1696357260000,
          "userId": 3,
           "policyNo": "POL-1234",
           "licenseNo": "MH123458",
           "claimAmount": 5000.0,
           "reason": "Accident",
            "incidentDate": "2025-10-01",
            "status": "PENDING"
        }

4️⃣ Update Claim

     PUT URL:

     http://localhost:8084/claims/update/1696357260000?status=APPROVED&claimAmount=6000


Response:

       { 
        "claimId": 1696357260000,
         "userId": 3,
         "policyNo": "POL-1234",
         "licenseNo": "MH123458",
         "claimAmount": 6000.0,
         "reason": "Accident",
           "incidentDate": "2025-10-01",
         "status": "APPROVED"
        }

Update Data 
 
       http://localhost:8084/claims/update/1?status=done&claimAmount=12025

Claim By Policy Details

    GET URL:

        http://localhost:8084/claims/with-policy/1696357260000


Response:
         
      {
      "claim": {
         "claimId": 1696357260000,
         "userId": 3,
          "policyNo": "POL-1234",
          "licenseNo": "MH123458",
           "claimAmount": 5000.0,
           "reason": "Accident",
           "incidentDate": "2025-10-01",
          "status": "PENDING"
       },
         "policy": {
              "id": 101,
               "userId": 3,
               "policyNumber": "POL-1234",
                "type": "Car",
               "premiumAmount": 5000.0,
               "startDate": "2025-01-01",
               "endDate": "2025-12-31"
              
}
}
