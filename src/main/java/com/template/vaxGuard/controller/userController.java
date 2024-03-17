package com.template.vaxGuard.controller;

import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.clinic;
import com.template.vaxGuard.models.vaccineCandidate;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.clinicRepository;
import com.template.vaxGuard.repositories.vaccineCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List clinicAddressList(){

        List<String> clinicAddressList = new ArrayList<>();
        List<clinic> clinicLists = clinicRepository.findAll();

        for(int i=0; i<clinicLists.size(); i++){
            clinicAddressList.add(clinicLists.get(i).getAddress());
        }
        return clinicAddressList;
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
        model.addAttribute("clinicAddressList", clinicAddressList());
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



    @GetMapping("/updateVaccineCandidateProfile")
    public String updateVaccineCandidateProfile(@ModelAttribute("vaccineCandidate") vaccineCandidate vaccineCandidate, Model model, HttpSession session){

        vaccineCandidate candidate = candidateRepository.findByEmail(vaccineCandidate.getEmail()); // Checking if the email really exists or not

        try{
            if(candidate != null){
                candidate.setFatherName(vaccineCandidate.getFatherName());
                candidate.setMotherName(vaccineCandidate.getMotherName());
                candidate.setPreferredAddress(vaccineCandidate.getPreferredAddress());

                candidateRepository.save(candidate);
                model.addAttribute("currentUser", candidate);
                session.setAttribute("message", new Message("Your Profile is Updated!", "alert-success"));
            }
        }catch (Exception exception){
            exception.printStackTrace();
            session.setAttribute("message", new Message("Sorry, couldn't update!", "alert-danger"));

        }

        return "redirect:/vaccineCandidate/userProfile";
    }











}
