package com.example.TugasBesar;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class AuthorizationAspect {

    @Autowired
    private HttpSession session;
    
    @Before("@annotation(requiredRole)")
    public void checkAuthorization(JoinPoint joinPoint, RequiredRole requiredRole) throws Throwable {
        //Memeriksa apakah terdapat atribut session dengan nama “username”
        String username = (String) session.getAttribute("username");
        if(username == null){
            throw new SecurityException("Session not found. User not logged in.");
        }

        String[] roles = requiredRole.value();

        //Memeriksa apakah terdapat role dengan nama “*” yang berarti role apapun akan diperbolehkan.
        if(Arrays.asList(roles).contains("*")){
            return;
        }

        String role = (String) session.getAttribute("role");
        System.out.println("Session Role: " + role); // Tambahkan log untuk debugging

        // Periksa apakah role pengguna ada dalam daftar roles yang diperbolehkan
        if (!Arrays.asList(roles).contains(role)) {
            throw new SecurityException("User is not authorized to perform this action.");
        }
        

    }
}


