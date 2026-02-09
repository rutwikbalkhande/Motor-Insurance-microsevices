# Security Impliment
✅ auth-Service -> SignUp / Login Api

✔ Auth Service starts without generated password

✔ /auth/login works

✔ JWT token is generated

✔ Password comes from DB

✔ No Spring default security

👉 Step 1 (Auth Service) is DONE

🧠 FREEZE THE LEARNING (IMPORTANT)

Before moving ahead, remember this golden rule:

Auth Service = only login/signup + JWT generation
It never protects APIs


#  SignUp 

*{username password role} : new Account
 *role : not pass then -> auto-> "Role_user" select


      http://localhost:8086/auth/signup
    {
     "username": "amy",
     "password": "123",
    "role":"Admin"
    }


# login Post:
     http://localhost:8086/auth/login
    {
    "username": "amy",
    "password": "123",
    "role":""
    }

# Folder Structure
    auth-service
    ├── controller
    │   └── AuthController     (/auth/login, /auth/signup)
    ├── service
    │   └── AuthService
    ├── entity
    │   └── User            
    ├── repository
    │   └── UserRepository
    ├── security
    │   ├── JwtUtil            (CREATE token)
    │   └── SecurityConfig     (permit login/signup)

# after that "Validation impliment in Api gateway "  
    ✅ WHAT YOU IMPLEMENT HERE
      What	Why
       JWT validation	Reject fake tokens
       Gateway filter	Intercept requests
       Route configs	Forward to services
       Header forwarding	Pass user info

# ❌ WHAT YOU DO NOT PUT HERE

    ❌ API Gateway logic
    ❌ Role-based access to business APIs
    ❌ Any /user/** or /admin/** APIs


✅ Api gateway  -> Route 7 & Authenticate user and return jwt tocan to access api {use tocken when call Apis}

    1. pom.xml : spring Security dependancy added
     2. conmfig : securityConfig file added
    3. UserController : each method add @PreAuthorize. if not use annotation -> all user access not annoted method
    4. // USER + ADMIN Access
          @PreAuthorize("hasAnyRole('USER','ADMIN')")
    5. // ADMIN Access only
          @PreAuthorize("hasRole('ADMIN')")
