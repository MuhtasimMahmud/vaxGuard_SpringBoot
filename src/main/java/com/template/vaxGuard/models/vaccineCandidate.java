package com.template.vaxGuard.models;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class vaccineCandidate{

    private String babyName;
    private Date birthDate;
    private Time birthTime;
    private String birthHospitalName;
    private String email;
    @Id
    private int birthID;
    @OneToMany
    private List<userTakenVaccines> takenVaccinesList;
    @OneToMany
    private List<userPendingVaccines> pendingVaccinesList;


    public vaccineCandidate(){
        //Default Constructor
    }

    public vaccineCandidate(String babyName, Date birthDate, Time birthTime, String birthHospitalName, String email, int birthID, List<userTakenVaccines> takenVaccinesList, List<userPendingVaccines> pendingVaccinesList) {
        this.babyName = babyName;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.birthHospitalName = birthHospitalName;
        this.email = email;
        this.birthID = birthID;
        this.takenVaccinesList = takenVaccinesList;
        this.pendingVaccinesList = pendingVaccinesList;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Time getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(Time birthTime) {
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
}
