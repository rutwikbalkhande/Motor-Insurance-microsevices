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

    POST URL: http://localhost:8084/claims/save

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

    GET URL: http://localhost:8084/claims/6
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

    GET URL: http://localhost:8084/claims/license/MH123457

         Response: {
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

     PUT URL: http://localhost:8084/claims/update/1696357260000?status=APPROVED&claimAmount=6000


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

    GET URL: http://localhost:8084/claims/with-policy/1696357260000


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
--------------------------------------------------------------------------------------

# 🚗 Claim Service APIs

---

# 1️⃣ Save Claim

         POST URL: http://localhost:8084/claims/save
```json
{
  "licenseNo": "MH123456",
  "claimAmount": 25000,
  "status": "PENDING"
}
```

---

# 2️⃣ Get All Claims

## GET URL

```http
http://localhost:8084/claims/all
```

---

# 3️⃣ Get Claim By ID

## GET URL

```http
http://localhost:8084/claims/1
```

---

# 4️⃣ Get Claim By License Number

## GET URL

```http
http://localhost:8084/claims/license/MH123456
```

---

# 5️⃣ Update Claim

## PUT URL

```http
http://localhost:8084/claims/update/1?status=APPROVED&claimAmount=50000
```

---

# 6️⃣ Upload Claim File

Supports:

- PDF
- JPG
- PNG

## POST URL

```http
http://localhost:8084/claims/upload
```

---

# Postman Steps

## Step 1

Select:

```text
POST
```

---

## Step 2

Go to:

```text
Body
```

---

## Step 3

Select:

```text
form-data
```

---

## Step 4

Add Keys

| KEY | TYPE | VALUE |
|---|---|---|
| file | File | Select PDF/JPG |
| userId | Text | 2 |

---

# 7️⃣ Download Claim File

## GET URL

```http
http://localhost:8084/claims/download/2
```

---

# Postman Steps

## Step 1

Select:

```text
GET
```

---

## Step 2

Enter URL

```http
http://localhost:8084/claims/download/2
```

---

## Step 3

Click:

```text
Send
```

---

# Base URL

```http
http://localhost:8084/claims
```