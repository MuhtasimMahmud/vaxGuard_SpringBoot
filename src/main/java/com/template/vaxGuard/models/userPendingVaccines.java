package com.template.vaxGuard.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class userPendingVaccines {

    private String vaccineName;
    private LocalDate takingDate;
    private String takingClinicName;
    @Id
    private String vaccineID;
    private int numberOfDose;


    public userPendingVaccines() {
        //Default Constructor
    }

    public userPendingVaccines(String vaccineName, LocalDate takingDate, String takingClinicName, String vaccineID, int numberOfDose) {
        this.vaccineName = vaccineName;
        this.takingDate = takingDate;
        this.takingClinicName = takingClinicName;
        this.vaccineID = vaccineID;
        this.numberOfDose = numberOfDose;
    }

    public int getNumberOfDose() {
        return numberOfDose;
    }

    public void setNumberOfDose(int numberOfDose) {
        this.numberOfDose = numberOfDose;
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

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }
}
