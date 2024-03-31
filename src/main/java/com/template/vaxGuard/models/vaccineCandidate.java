package com.template.vaxGuard.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Entity
public class vaccineCandidate{

    private String babyName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime birthTime;
    private String birthHospitalName;
    private String email;
    @Id
    private int birthID;
    private String vaccineTakingClinicName;
    private String fatherName;
    private String motherName;
    private String preferredAddress;

    @OneToMany(mappedBy = "vaccineCandidate", cascade = CascadeType.ALL)
    private List<userPendingVaccines> pendingVaccinesList;


    private List<userTakenVaccines> takenVaccinesList;


    public vaccineCandidate(){

        //Default Constructor
    }

    public vaccineCandidate(String babyName, LocalDate birthDate, LocalTime birthTime, String birthHospitalName, String email, int birthID, List<userTakenVaccines> takenVaccinesList, List<userPendingVaccines> pendingVaccinesList, String vaccineTakingClinicName) {
        this.babyName = babyName;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.birthHospitalName = birthHospitalName;
        this.email = email;
        this.birthID = birthID;
        this.takenVaccinesList = takenVaccinesList;
        this.pendingVaccinesList = pendingVaccinesList;
        this.vaccineTakingClinicName = vaccineTakingClinicName;
    }

    public String getVaccineTakingClinicName() {
        return vaccineTakingClinicName;
    }

    public void setVaccineTakingClinicName(String vaccineTakingClinicName) {
        this.vaccineTakingClinicName = vaccineTakingClinicName;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalTime getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(LocalTime birthTime) {
        this.birthTime = birthTime;
    }

    public String getBirthHospitalName() {
        return birthHospitalName;
    }

    public void setBirthHospitalName(String birthHospitalName) {
        this.birthHospitalName = birthHospitalName;
    }

    public int getBirthID() {
        return birthID;
    }

    public void setBirthID(int birthID) {
        this.birthID = birthID;
    }

    public List<userTakenVaccines> getTakenVaccinesList() {
        return takenVaccinesList;
    }

    public void setTakenVaccinesList(List<userTakenVaccines> takenVaccinesList) {
        this.takenVaccinesList = takenVaccinesList;
    }

    public List<userPendingVaccines> getPendingVaccinesList() {
        return pendingVaccinesList;
    }

    public void setPendingVaccinesList(List<userPendingVaccines> pendingVaccinesList) {
        this.pendingVaccinesList = pendingVaccinesList;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPreferredAddress() {
        return preferredAddress;
    }

    public void setPreferredAddress(String address) {
        this.preferredAddress = address;
    }
}
