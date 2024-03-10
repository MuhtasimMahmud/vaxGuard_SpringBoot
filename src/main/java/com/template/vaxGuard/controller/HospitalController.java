package com.template.vaxGuard.controller;

import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.pendingCandidateFromHospital;
import com.template.vaxGuard.repositories.pendingCandidateFromHospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HospitalController {


    @Autowired
    pendingCandidateFromHospitalRepository pendingCandidateRepository;

    @GetMapping("hospitalLogin")
    public String hospitalLogin(){
        return "hospitalLogin";
    }

    // handle for hospital Login
    @GetMapping("childBirthRegistration")
    public String childBirthRegistration(Model model){
        model.addAttribute("pendingCandidate", new pendingCandidateFromHospital());
        return "GovtHospitalORhealthCareAuthority/childBirthRegistration";
    }

    @RequestMapping(value = "do_pendingCandidateRegistration", method = RequestMethod.POST)
    public String do_pendingCandidateRegistration(@ModelAttribute("pendingCandidateObject")pendingCandidateFromHospital pendingCandidate, Model model, HttpSession session){

        try{

            pendingCandidateFromHospital checkDuplicate = pendingCandidateRepository.findByEmail(pendingCandidate.getEmail());
            if(checkDuplicate == null){
                pendingCandidateFromHospital result = pendingCandidateRepository.save(pendingCandidate);
                model.addAttribute("pendingCandidate", new pendingCandidateFromHospital());
                session.setAttribute("message", new Message("Successfully Registered! Birth ID is : "+result.getBirthID(), "alert-success"));

            }else{
                model.addAttribute("pendingCandidate", pendingCandidate);
                session.setAttribute("message", new Message("Please change the email address. This address is already in use!!", "alert-danger"));
            }
            return "GovtHospitalORhealthCareAuthority/childBirthRegistration";


        }catch (Exception exception){
            exception.printStackTrace();
            model.addAttribute("pendingCandidate", new pendingCandidateFromHospital());
            session.setAttribute("message", new Message("Something went wrong!" + exception.getMessage(), "alert-danger"));

            return "GovtHospitalORhealthCareAuthority/childBirthRegistration";
        }

    }


}
