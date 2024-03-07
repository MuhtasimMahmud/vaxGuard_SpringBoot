package com.template.vaxGuard.controller;

import com.template.vaxGuard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {


    @Autowired
    UserRepository userRepository;


    // handler for custom login
    @GetMapping("/login")
    public String customLogin(Model model){
        model.addAttribute("title", "Login");
        return "login";
    }


    // handle for hospital Login
    @GetMapping("childBirthRegistration")
    public String childBirthRegistration(){
        return "GovtHospitalORhealthCareAuthority/childBirthRegistration";
    }

    @GetMapping("hospitalLogin")
    public String hospitalLogin(){
        return "hospitalLogin";
    }

}
