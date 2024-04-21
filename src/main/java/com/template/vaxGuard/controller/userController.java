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
            clinicNames.add(clinicLists.get(i).getName() + ". [" + clinicLists.get(i).getAddress() + "]");
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

        String clinicWithAddress = vaccineCandidate.getPreferredClinic();
        String chosenClinic = clinicWithAddress.substring(0, clinicWithAddress.indexOf('.'));

        vaccineCandidate existingCandidate = candidateRepository.findByEmail(vaccineCandidate.getEmail());
        String existingPreferredClinic = existingCandidate.getPreferredClinic();

        try{
            if(existingCandidate != null){
                existingCandidate.setBabyName(vaccineCandidate.getBabyName());
                existingCandidate.setFatherName(vaccineCandidate.getFatherName());
                existingCandidate.setMotherName(vaccineCandidate.getMotherName());

                if(existingCandidate.getClinicRequestStatus().equals("Pending")){
                    session.setAttribute("message", new Message("Sorry you've already a pending request of clinic! You can't make a request until any decision of the previous one ! But your other information is updated.", "alert-danger"));

                } else if (chosenClinic == null) {
                    existingCandidate.setClinicRequestStatus(existingCandidate.getClinicRequestStatus());
                } else if(existingPreferredClinic == null && chosenClinic != null){
                    clinicRepository.findByName(chosenClinic).getRequests().add(existingCandidate);
                    existingCandidate.setClinicRequestStatus("Pending");
                    session.setAttribute("message", new Message("Your request is sent to the clinic! Wait until the clinic response.", "alert-success"));
                } else if (existingPreferredClinic != null && !existingPreferredClinic.equals(chosenClinic)) {
                    clinicRepository.findByName(chosenClinic).getRequests().add(existingCandidate);
                    clinicRepository.findByName(existingPreferredClinic).getRequests().remove(existingCandidate);
                    existingCandidate.setClinicRequestStatus("Pending");

                    session.setAttribute("message", new Message("Your request for changing the clinic is processing ! Wait until the clinic response.", "alert-success"));
                } else if (existingPreferredClinic != null && chosenClinic != null && existingPreferredClinic.equals(chosenClinic)) {
                    existingCandidate.setPreferredClinic(existingPreferredClinic);
                }



            }

            candidateRepository.save(existingCandidate);


        }catch (Exception exception){
            exception.printStackTrace();
        }


        // jodi preferred clinic change hoy taile notun clinic e request pathaite hobe





        // "Your clinic"
        // Options :
        // - request pending for clinic Name
        // - clinic Name





        return "redirect:/vaccineCandidate/userProfile";
    }







}
