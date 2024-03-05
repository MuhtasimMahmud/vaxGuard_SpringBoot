package com.template.vaxGuard.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class clinicVaccines {

    @Id
    private String vaccineId;
    private String vaccineName;
    private String quantity;
    private String expiredDate;

    public clinicVaccines(){
        //Default Constructor
    }

    public clinicVaccines(String vaccineId, String vaccineName, String quantity, String expiredDate) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.quantity = quantity;
        this.expiredDate = expiredDate;
    }


    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
