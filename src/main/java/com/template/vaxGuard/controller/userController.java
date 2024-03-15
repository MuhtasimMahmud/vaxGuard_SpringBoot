package com.template.vaxGuard.controller;

import com.template.vaxGuard.models.User;
import com.template.vaxGuard.models.vaccineCandidate;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.vaccineCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/vaccineCandidate")
public class userController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    vaccineCandidateRepository candidateRepository;

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


    @ResponseBody
    @GetMapping("/currentLoggedInUserName")
    public String currentUser(Principal principal){

        String username = principal.getName();
        System.out.println("principal username :" + username);

        vaccineCandidate candidate = candidateRepository.findByEmail(username);

        System.out.println("candidate username :" + candidate.getBabyName());


        return candidate.getBabyName();
    }

}
