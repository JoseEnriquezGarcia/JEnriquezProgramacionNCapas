package com.digis01JEnriquezProgramacionNCapas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String Login(){
        return "Login";
    }
    
    @GetMapping("/logout")
    public String LogoutMod(){
        return "Logout";
    }
    
    @PostMapping("/logout")
    public String Logout(){
        return "redirect:/login";
    }
    
    @GetMapping("/error")
    public String AccessDenied(){
        return "Error";
    }
}
