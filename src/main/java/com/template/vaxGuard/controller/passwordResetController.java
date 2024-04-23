package com.template.vaxGuard.controller;


import com.template.vaxGuard.email.emailSenderService;
import com.template.vaxGuard.models.User;
import com.template.vaxGuard.models.resetPasswordEntity;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.resetPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class passwordResetController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    resetPasswordRepository resetPasswordRepository;

    @Autowired
    private emailSenderService senderService;

    public void sendMail(String userEmail, String subject, String body){
        senderService.sendEmail(userEmail, subject, body);
    }



    @GetMapping("/codeRequestPage")
    public String codeRequestPage(){
        return "ForgotPassword/codeRequestPage";
    }

    @GetMapping("")
    public String nullUser(){
        return "ForgotPassword/nullUser";
    }

    @GetMapping("/sendCode")
    public String sendCode(@RequestParam("email") String email, Model model){

        User user = userRepository.findByEmail(email);
        String url = "";

        if(user == null){
            url = "ForgotPassword/nullUser";


        }else{
            model.addAttribute("email", email);

            resetPasswordEntity existingEntity  = resetPasswordRepository.findByEmail(email);


            Random rand = new Random();

            // Generate random integers in range 0 to 9999
            int code = rand.nextInt(10000);

            if(existingEntity == null){
                resetPasswordEntity newEntity = new resetPasswordEntity();
                newEntity.setEmail(email);
                newEntity.setResetCode(code);

                resetPasswordRepository.save(newEntity);
            }else{
                existingEntity.setResetCode(code);
            }


            sendMail(email, "Password Reset Code", "Your password reset code is : "  + code);

            url = "ForgotPassword/submitCodePage";
        }
        return url;
    }

    @GetMapping("/submitCodePage")
    public String submitCodePage(){
        return "ForgotPassword/submitCodePage";
    }


    @GetMapping("/doSubmitCode")
    public String doSubmitCode(@RequestParam("code") int code, @RequestParam("email") String email, HttpSession session){

        int resetCode = resetPasswordRepository.findByEmail(email).getResetCode();
        if(resetCode == code){

        }
        return "ForgotPassword/resetPasswordPage";
    }

    @GetMapping("/resetPasswordPage")
    public String resetPasswordPage(){
        return "ForgotPassword/resetPasswordPage";
    }

















}
