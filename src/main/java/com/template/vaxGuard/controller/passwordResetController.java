package com.template.vaxGuard.controller;


import com.template.vaxGuard.email.emailSenderService;
import com.template.vaxGuard.helper.Message;
import com.template.vaxGuard.models.User;
import com.template.vaxGuard.models.resetPasswordEntity;
import com.template.vaxGuard.repositories.UserRepository;
import com.template.vaxGuard.repositories.resetPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class passwordResetController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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

    @GetMapping("/sendCode")
    public String sendCode(@RequestParam("email") String email, Model model, HttpSession session){

        User user = userRepository.findByEmail(email);
        String url = "";

        if(user == null){
            session.setAttribute("message", new Message("Sorry there is no user of this email address!", "alert-danger"));
            url =  "ForgotPassword/codeRequestPage";

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
                resetPasswordRepository.save(existingEntity);
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
    public String doSubmitCode(@RequestParam("code") int code, @RequestParam("email") String email, HttpSession session, Model model){

        int resetCode = resetPasswordRepository.findByEmail(email).getResetCode();
        String url = "";

        if(resetCode == code){
            url = "ForgotPassword/resetPasswordPage";
        }else{
            session.setAttribute("message", new Message("Sorry the code doesn't match!", "alert-danger"));
            url = "ForgotPassword/submitCodePage";
        }

        model.addAttribute("email", email);

        return url;
    }

    @GetMapping("/resetPasswordPage")
    public String resetPasswordPage(){
        return "ForgotPassword/resetPasswordPage";
    }

    @GetMapping("/doResetPassword")
    public String doResetPassword(@RequestParam("password")String password, @RequestParam("RetypePassword")String retypePassword,
                                  @RequestParam("email") String email, HttpSession session, Model model){

        User existingUser = userRepository.findByEmail(email);
        String url = "";

        try{
            if(existingUser != null){

                if(password.equals(retypePassword)){
                    existingUser.setPassword(passwordEncoder.encode(password));
                    userRepository.save(existingUser);
                    session.setAttribute("message", new Message("Your password is successfully changed!, Now you can login!", "alert-success"));

                }else{
                    session.setAttribute("message", new Message("Sorry the password and retype password doesn't match!", "alert-danger"));
                    model.addAttribute("email", email);
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return "ForgotPassword/resetPasswordPage";
    }

















}
