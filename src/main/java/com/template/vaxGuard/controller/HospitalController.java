package com.template.vaxGuard.controller;

import com.template.vaxGuard.email.emailSenderService;
import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.User;
import com.template.vaxGuard.models.pendingCandidateFromHospital;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.pendingCandidateFromHospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class HospitalController {


    @Autowired
    private emailSenderService senderService;

    public void sendMail(String userEmail, String subject, String body){
        senderService.sendEmail(userEmail, subject, body);
    }


    @Autowired
    pendingCandidateFromHospitalRepository pendingCandidateRepository;

    @Autowired
    UserRepository userRepository;

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
    public String do_pendingCandidateRegistration(@ModelAttribute("pendingCandidateObject")pendingCandidateFromHospital pendingCandidate,
                                                  @RequestParam("passcode")String passcode, Model model, HttpSession session){

        try{

            if(passcode.equals("passcode1212")){
                LocalDate birthDate = pendingCandidate.getBirthDate();
                pendingCandidateFromHospital checkDuplicate = pendingCandidateRepository.findByEmail(pendingCandidate.getEmail());
                User checkUserDuplicate = userRepository.findByEmail(pendingCandidate.getEmail());

                if(checkDuplicate == null && checkUserDuplicate == null){

                    pendingCandidateFromHospital result = pendingCandidateRepository.save(pendingCandidate);
                    model.addAttribute("pendingCandidate", new pendingCandidateFromHospital());
                    session.setAttribute("message", new Message("Successfully Registered! Birth ID is : "+result.getBirthID()+". Now user just need to register an account with password along this birthID and email.", "alert-success"));

                    sendMail(pendingCandidate.getEmail(), "Account Creation",
                            "Congratulation you've been successfully registered! Your birth Id is : "+result.getBirthID());

                }else{
                    model.addAttribute("pendingCandidate", pendingCandidate);
                    session.setAttribute("message", new Message("Please change the email address. This address is already in use!!", "alert-danger"));
                }
            }else{
                model.addAttribute("pendingCandidate", pendingCandidate);
                session.setAttribute("message", new Message("The passcode is incorrect !", "alert-danger"));
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
