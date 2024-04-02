package com.template.vaxGuard.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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
    private String preferredClinic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<vaccines> pendingVaccines = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<vaccines> takenVaccines = new ArrayList<>();

    @ManyToOne
    private clinic clinic;

    public vaccineCandidate(){
        //Default Constructor
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthID() {
        return birthID;
    }

    public void setBirthID(int birthID) {
        this.birthID = birthID;
    }

    public String getVaccineTakingClinicName() {
        return vaccineTakingClinicName;
    }

    public void setVaccineTakingClinicName(String vaccineTakingClinicName) {
        this.vaccineTakingClinicName = vaccineTakingClinicName;
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

    public String getPreferredClinic() {
        return preferredClinic;
    }

    public void setPreferredClinic(String preferredAddress) {
        this.preferredClinic = preferredAddress;
    }

    public List<vaccines> getPendingVaccines() {
        return pendingVaccines;
    }

    public void setPendingVaccines(List<vaccines> pendingVaccines) {
        this.pendingVaccines = pendingVaccines;
    }

    public List<vaccines> getTakenVaccines() {
        return takenVaccines;
    }

    public void setTakenVaccines(List<vaccines> takenVaccines) {
        this.takenVaccines = takenVaccines;
    }
}
