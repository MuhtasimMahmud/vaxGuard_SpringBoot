package com.template.vaxGuard.controller;


import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.*;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.clinicRepository;
import com.template.vaxGuard.repositories.pendingCandidateFromHospitalRepository;
import com.template.vaxGuard.repositories.vaccineCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class signUpController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private vaccineCandidateRepository vaccineCandidateRepository;

    @Autowired
    private pendingCandidateFromHospitalRepository pendingCandidateRepository;

    @Autowired
    private clinicRepository clinicRepository;


    @RequestMapping("/userSignUp")
    public String userSignUP(Model model){
        model.addAttribute("title", "User Register Account");
        model.addAttribute("user", new vaccineCandidate());
        return "User/userSignUp";
    }

    @RequestMapping("clinicSignUp")
    public String clinicSignUp(Model model){
        model.addAttribute("title", "Clinic Register Account");
        model.addAttribute("clinic", new clinic());
        return "vaccineGivingClinic/clinicSignUp";
    }




    @RequestMapping("/doUserRegistration")
    public String doUserRegistration(@ModelAttribute("newUser")vaccineCandidate candidate, Model model, HttpSession session,
                                     @RequestParam("password") String password){

        try{
            User user_duplicateCheck = userRepository.findByEmail(candidate.getEmail());
            vaccineCandidate candidate_duplicateCheck = vaccineCandidateRepository.findByEmail(candidate.getEmail());
            pendingCandidateFromHospital has_pendingCandidate = pendingCandidateRepository.findByEmail(candidate.getEmail());

            if(has_pendingCandidate == null){
                session.setAttribute("message", new Message("Sorry, You are not authorized to register an account because you've no record of hospital !!", "alert-danger"));
                model.addAttribute("user", candidate);

            }else if(user_duplicateCheck != null || candidate_duplicateCheck !=  null){
                session.setAttribute("message", new Message("Sorry, this email address is already in use of another account !", "alert-danger"));
                model.addAttribute("user", candidate);

            }else if(has_pendingCandidate.getBirthID() != candidate.getBirthID()){
                session.setAttribute("message", new Message("Sorry, birth id doesn't match with the hospital given birth id !", "alert-danger"));
                model.addAttribute("user", candidate);

            }else{

                // Entry in the vaccineCandidate table
                pendingCandidateFromHospital pendingCandidate = pendingCandidateRepository.findByEmail(candidate.getEmail());
                candidate.setBirthDate(pendingCandidate.getBirthDate());
                candidate.setBirthTime(pendingCandidate.getBirthTime());
                candidate.setBirthHospitalName(pendingCandidate.getBirthHospitalName());
                candidate.setClinicRequestStatus("Not Requested yet");

                List<vaccines> pendingVaccines = new ArrayList<>();

                pendingVaccines.add(new vaccines("Polio", candidate.getBirthDate().plusDays(10), candidate.getVaccineTakingClinicName(), "1st", false, candidate.getBirthID() ));
                pendingVaccines.add(new vaccines("Polio", candidate.getBirthDate().plusDays(100), candidate.getVaccineTakingClinicName(), "2nd", false, candidate.getBirthID() ));

                candidate.setPendingVaccines(pendingVaccines);

                vaccineCandidate resultCandidate = this.vaccineCandidateRepository.save(candidate);


                // Entry in the user table
                User newUser = new User();
                newUser.setEmail(candidate.getEmail());
                newUser.setRole("ROLE_USER");
                newUser.setPassword(passwordEncoder.encode(password));
                User result = userRepository.save(newUser);



                // remove from the pendingCandidateFromHospital list
                pendingCandidateRepository.delete(has_pendingCandidate);


                session.setAttribute("message", new Message("Successfully Registered your account !!", "alert-success"));
                model.addAttribute("user", new vaccineCandidate());
            }
            return "User/userSignUp";
        }catch (Exception exception){
            exception.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong!!"+exception.getMessage(), "alert-danger"));
            model.addAttribute("user", new vaccineCandidate());
            return "User/userSignUp";
        }
    }

    @RequestMapping("/doClinicRegistration")
    public String clinicRegistration(@ModelAttribute("clinicObject")clinic clinicObject, Model model, HttpSession session,
                                     @RequestParam("password") String password){

        clinic clinicDuplicateCheck = clinicRepository.findByEmail(clinicObject.getEmail());
        User userDuplicateCheck = userRepository.findByEmail(clinicObject.getEmail());

        clinic hasSameName = clinicRepository.findByName(clinicObject.getName());

        try {
            if(clinicDuplicateCheck == null && userDuplicateCheck == null && hasSameName == null){
                User user = new User();
                user.setRole("ROLE_CLINIC");
                user.setEmail(clinicObject.getEmail());
                user.setPassword(passwordEncoder.encode(password));

                User userResult = userRepository.save(user);
                clinic clinicResult = clinicRepository.save(clinicObject);

                session.setAttribute("message", new Message("Successfully Registered your account !!", "alert-success"));
                model.addAttribute("clinic", new clinic());

            }else{
                session.setAttribute("message", new Message("Sorry, email or clinic name already exists!.", "alert-danger"));
                model.addAttribute("clinic", clinicObject);
            }

            return "vaccineGivingClinic/clinicSignUp";

        }catch (Exception exception){
            exception.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong!", "alert-danger"));
            model.addAttribute("clinic", clinicObject);
            return "vaccineGivingClinic/clinicSignUp";
        }


    }




}
