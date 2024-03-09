package com.template.vaxGuard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vaccineCandidate")
public class userController {


    @GetMapping("/user")
    public String normalUser(){
        return "User/user";
    }

    @GetMapping("/admin")
    public String adminUser(){
        return "Admin/admin";
    }

    @GetMapping("/userProfileOVerView")
    public String profileOverViewTab(){
        return "user/profileOverview";
    }

    @GetMapping("/userProfile")
    public String userProfileTab(){
        return "user/profile";
    }

    @GetMapping("/userEmail")
    public String emailTab(){
        return "user/email";
    }

    @GetMapping("/userPassword")
    public String passwordTab(){
        return "user/password";
    }

    @GetMapping("/userVaccineList")
    public String vaccineList(){
        return "user/Vaccine List";
    }

    @GetMapping("/userFAQ")
    public String userFAQ(){
        return "user/FAQ";
    }





}
