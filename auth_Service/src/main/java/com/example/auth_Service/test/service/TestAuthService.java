package com.example.auth_Service.test.service;


import com.example.auth_Service.test.entity.TestAuth;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TestAuthService {

           List<TestAuth> store= new ArrayList<>();

           public TestAuthService() {

        store.add(new TestAuth(UUID.randomUUID().toString(), "rutwik", "rutwik@gmail.com"));
        store.add(new TestAuth(UUID.randomUUID().toString(), "ram", "ram.com"));
        store.add(new TestAuth(UUID.randomUUID().toString(), "aniket", "aniket@gmail.com"));
        store.add(new TestAuth(UUID.randomUUID().toString(), "shyam", "shyam@gmail.com"));
           }

             public List<TestAuth> getuser(){
               return store;

    }
    public String currentlogedin(Principal principal)
    {
        return principal.getName();
    }

    }


