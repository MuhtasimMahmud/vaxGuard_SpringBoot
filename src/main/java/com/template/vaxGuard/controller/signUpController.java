package com.template.vaxGuard.controller;


import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.User;
import com.template.vaxGuard.models.pendingCandidateFromHospital;
import com.template.vaxGuard.models.vaccineCandidate;
import com.template.vaxGuard.repositories.UserRepository;
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


    @RequestMapping("/userSignUp")
    public String userSignUP(Model model){
        model.addAttribute("title", "User Register Account");
        model.addAttribute("user", new vaccineCandidate());
        return "User/userSignUp";
    }

    @RequestMapping("clinicSignUp")
    public String clinicSignUp(Model model){
        model.addAttribute("title", "Clinic Register Account");
        model.addAttribute("user", new User());
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

                // Entry in the user table

                User newUser = new User();
                newUser.setEmail(candidate.getEmail());
                newUser.setRole("ROLE_USER");
                newUser.setPassword(passwordEncoder.encode(password));
                User result = userRepository.save(newUser);


                // Entry in the vaccineCandidate table

                pendingCandidateFromHospital pendingCandidate = pendingCandidateRepository.findByEmail(candidate.getEmail());
                candidate.setBirthDate(pendingCandidate.getBirthDate());
                candidate.setBirthTime(pendingCandidate.getBirthTime());
                candidate.setBirthHospitalName(pendingCandidate.getBirthHospitalName());
                vaccineCandidate resultCandidate = this.vaccineCandidateRepository.save(candidate);


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







    //handler for registering user
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, Model model, HttpSession session){


        try{

            String role = "ROLE_"+user.getRole();
            user.setRole(role);

            User checkDuplicate = userRepository.findByEmail(user.getEmail());
            if(checkDuplicate == null){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                User UserResult = this.userRepository.save(user);
                model.addAttribute("user", new User());
                session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));

            }else{
                model.addAttribute("user", user);
                session.setAttribute("message", new Message("Please give an unique email address.", "alert-danger"));
            }

            return "signUp";


        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong !!" + e.getMessage(), "alert-danger"));

            return "signUp";
        }

    }

}
