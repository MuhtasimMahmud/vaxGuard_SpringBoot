package com.template.vaxGuard.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class passwordResetController {

    @GetMapping("/codeRequest")
    public String codeRequest(){
        return "ForgotPassword/codeRequest";
    }

    @GetMapping("/submitCodePage")
    public String sendCode(){
        return "ForgotPassword/submitCodePage";
    }

    @GetMapping("/resetPasswordPage")
    public String resetPasswordPage(){
        return "ForgotPassword/resetPasswordPage";
    }


    @GetMapping("/doSubmitCode")
    public String doSubmitCode(){
        return "ForgotPassword/resetPasswordPage";
    }















}
