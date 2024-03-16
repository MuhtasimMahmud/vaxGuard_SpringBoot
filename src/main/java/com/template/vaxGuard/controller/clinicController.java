package com.template.vaxGuard.controller;

import com.template.vaxGuard.models.clinic;
import com.template.vaxGuard.repositories.clinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/clinicAuthority")
public class clinicController {

    @Autowired
    clinicRepository clinicRepository;

    public clinic currentLoggedInClinic(Principal principal){
        String username = principal.getName();
        clinic clinic = clinicRepository.findByEmail(username);

        return clinic;
    }

    @GetMapping("clinicBase")
    public String clinicBase(){
        return "vaccineGivingClinic/clinicBase";
    }

    @GetMapping("/pushVaccine")
    public String pushVaccine(){
        return "vaccineGivingClinic/Push";
    }

    @GetMapping("/clinicRecords")
    public String records(){
        return "vaccineGivingClinic/records";
    }

    @GetMapping("/vaccineQuantity")
    public String vaccines(){
        return "vaccineGivingClinic/vaccines";
    }

    @GetMapping("/requests")
    public String requests(){
        return "vaccineGivingClinic/requests";
    }

    @GetMapping("/clinicProfile")
    public String profile(Principal principal, Model model){

        clinic clinic = currentLoggedInClinic(principal);
        model.addAttribute("clinic", clinic);

        return "vaccineGivingClinic/profile";
    }

}
