package com.template.vaxGuard.controller;

import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.User;
import com.template.vaxGuard.models.clinic;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.clinicRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.security.Principal;

@Controller
@RequestMapping("/clinicAuthority")
public class clinicController {

    @Autowired
    clinicRepository clinicRepository;

    @Autowired
    UserRepository userRepository;

    public clinic currentLoggedInClinic(Principal principal){
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

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

    @GetMapping("/updateClinicProfile")
    public String updateClinicProfile(@ModelAttribute("clinic")clinic clinic, Model model, Principal principal, HttpSession session){

        clinic existingClinic = clinicRepository.findByEmail(currentLoggedInClinic(principal).getEmail());


        try{
            if(existingClinic != null){
                existingClinic.setAddress(clinic.getAddress());
                existingClinic.setName(clinic.getName());
                clinicRepository.save(existingClinic);
                model.addAttribute("clinic", existingClinic);
                session.setAttribute("message", new Message("Your Profile is Updated", "alert-success"));

            }
        }catch (Exception exception){
            exception.printStackTrace();
            session.setAttribute("message", new Message("Sorry! Can't update your profile"+exception.getMessage(), "alert-danger"));
        }

        return "redirect:/clinicAuthority/clinicProfile";
    }

}
