package com.template.vaxGuard.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class vaccines {

    private String vaccineName;
    private LocalDate takingDate;
    private String takingClinicName;
    private String numberOfDose; // which number of dose it is - 1st/2nd/3rd etc
    private boolean isGiven; // ture / false

    @Id
    private String vID;


    @ManyToOne
    private vaccineCandidate candidate;


    public vaccines(){
        //Default Constructor
    }

    public vaccines(String vaccineName, LocalDate takingDate, String takingClinicName, String numberOfDose, boolean isGiven, int candidateBirthID) {
        this.vaccineName = vaccineName;
        this.takingDate = takingDate;
        this.takingClinicName = takingClinicName;
        this.numberOfDose = numberOfDose;
        this.isGiven = isGiven;
        this.vID = vaccineName+"_"+numberOfDose+ "_" + candidateBirthID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getTakingDate() {
        return takingDate;
    }

    public void setTakingDate(LocalDate takingDate) {
        this.takingDate = takingDate;
    }

    public String getTakingClinicName() {
        return takingClinicName;
    }

    public void setTakingClinicName(String takingClinicName) {
        this.takingClinicName = takingClinicName;
    }

    public String getNumberOfDose() {
        return numberOfDose;
    }

    public void setNumberOfDose(String numberOfDose) {
        this.numberOfDose = numberOfDose;
    }

    public boolean isGiven() {
        return isGiven;
    }

    public void setGiven(boolean given) {
        isGiven = given;
    }

    public String getvID() {
        return vID;
    }

    public void setvID(String vID) {
        this.vID = vID;
    }

    public vaccineCandidate getCandidate() {
        return candidate;
    }

    public void setCandidate(vaccineCandidate candidate) {
        this.candidate = candidate;
    }
}
