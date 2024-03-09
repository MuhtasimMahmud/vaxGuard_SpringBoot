package com.template.vaxGuard.controller;

import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.pendingVaccineCandidateFromHospital;
import com.template.vaxGuard.repositories.pendingVaccineCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class HospitalController {


    @Autowired
    pendingVaccineCandidateRepository pendingVaccineCandidateRepository;

    @GetMapping("hospitalLogin")
    public String hospitalLogin(){
        return "hospitalLogin";
    }

    // handle for hospital Login
    @GetMapping("childBirthRegistration")
    public String childBirthRegistration(){
        return "GovtHospitalORhealthCareAuthority/childBirthRegistration";
    }

    @GetMapping("do_pendingVaccineCandidateRegistration")
    public String doRegistration(@ModelAttribute("pendingVaccineCandidate")pendingVaccineCandidateFromHospital pendingVaccineCandidateFromHospital, Model model, HttpSession httpSession){

        try{

            if(pendingVaccineCandidateRepository.findByEmail(pendingVaccineCandidateFromHospital.getEmail()) == null){
                pendingVaccineCandidateRepository.save(pendingVaccineCandidateFromHospital);
            }else{
                model.addAttribute("pendingCandidate", pendingVaccineCandidateFromHospital);
                httpSession.setAttribute("message", new Message("The email address is already in use!", "alert-danger"));
            }

            return "GovtHospitalORhealthCareAuthority/childBirthRegistration";

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("pendingCandidate", pendingVaccineCandidateFromHospital);
            httpSession.setAttribute("message", new Message("Something went wrong !!" + e.getMessage(), "alert-danger"));

            return "GovtHospitalORhealthCareAuthority/childBirthRegistration";
        }

    }


}
