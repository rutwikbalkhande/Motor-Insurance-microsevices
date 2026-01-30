#  SignUp 

*{username password role} : new Account
 *role : not pass then -> auto "user" select

      http://localhost:8086/auth/signup
    {
     "username": "amy",
     "password": "123",
    "role":""
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