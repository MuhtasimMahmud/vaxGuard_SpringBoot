package com.template.vaxGuard.controller;

import com.template.vaxGuard.config.MyConfig;
import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.User;
import com.template.vaxGuard.models.clinic;
import com.template.vaxGuard.models.vaccineCandidate;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.clinicRepository;
import com.template.vaxGuard.repositories.vaccineCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vaccineCandidate")
public class userController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    vaccineCandidateRepository candidateRepository;

    @Autowired
    clinicRepository clinicRepository;


    public vaccineCandidate currentUser(Principal principal){

        String username = principal.getName();
        vaccineCandidate candidate = candidateRepository.findByEmail(username);

        return candidate;
    }

    public List clinicLists(){

        List<clinic> clinicLists = clinicRepository.findAll();
        List<String> clinicNames = new ArrayList<>();

        for(int i=0; i<clinicLists.size(); i++){
            clinicNames.add(clinicLists.get(i).getName());
        }
        return clinicNames;
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
        model.addAttribute("clinicNames", clinicLists());
        return "user/profile";
    }


    @GetMapping("/userPassword")
    public String passwordTab(){
        return "user/password";
    }

    @GetMapping("/userTakenVaccineList")
    public String takenVaccines(){
        return "user/takenVaccineList";
    }


    @GetMapping("/userPendingVaccineList")
    public String vaccineList(){
        return "user/pendingVaccineList";
    }



    @GetMapping("/userFAQ")
    public String userFAQ(){
        return "user/FAQ";
    }



    @GetMapping("/updateVaccineCandidateProfile")
    public String updateVaccineCandidateProfile(@ModelAttribute("vaccineCandidate") vaccineCandidate vaccineCandidate, Model model, HttpSession session){




        return "redirect:/vaccineCandidate/userProfile";
    }







}
