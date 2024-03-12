package com.template.vaxGuard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clinicAuthority")
public class clinicController {

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
    public String profile(){
        return "vaccineGivingClinic/profile";
    }

}
