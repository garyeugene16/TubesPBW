//tidak dipake sepertinya

package com.example.TugasBesar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String index(HttpSession session) {
        if(session.getAttribute("user") != null){
            return "/member/home-member";
        }
        return "guest/home";
    }
}