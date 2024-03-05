package com.template.vaxGuard.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class userPendingVaccines {

    private String vaccineName;
    private Date takingDate;
    private String takingClinicName;
    @Id
    private String vaccineID;


    public userPendingVaccines(){
        //Default Constructor
    }


    public userPendingVaccines(String vaccineName, Date takingDate, String takingClinicName) {
        this.vaccineName = vaccineName;
        this.takingDate = takingDate;
        this.takingClinicName = takingClinicName;
    }


    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Date getTakingDate() {
        return takingDate;
    }

    public void setTakingDate(Date takingDate) {
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
