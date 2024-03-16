package com.template.vaxGuard.controller;

import com.template.vaxGuard.models.vaccineCandidate;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.vaccineCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    public vaccineCandidate currentUser(Principal principal){

        String username = principal.getName();
        vaccineCandidate candidate = candidateRepository.findByEmail(username);

        return candidate;
    }


    @ResponseBody
    @GetMapping("/currentLoggedInUserName")
    public String currentUserName(Principal principal){
        return currentUser(principal).getBabyName();
    }


    @GetMapping("/userProfileOVerView")
    public String profileOverViewTab(Model model, Principal principal){

        model.addAttribute("currentUser", currentUser(principal));
        return "user/profileOverview";
    }


    @GetMapping("/userProfile")
    public String userProfileTab(Model model, Principal principal){

        model.addAttribute("currentUser", currentUser(principal));
        return "user/profile";
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
