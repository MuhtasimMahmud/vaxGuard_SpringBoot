package com.template.vaxGuard.models;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class vaccineCandidate extends User{

    private String BabyName;
    private Date birthDate;
    private Time birthTime;
    private String birthHospitalName;
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String birthID;
    @OneToMany
    private List<userTakenVaccines> takenVaccinesList;
    @OneToMany
    private List<userPendingVaccines> pendingVaccinesList;


    public vaccineCandidate(){
        //Default Constructor
    }

    public vaccineCandidate(String email, String role, String password, String babyName, Date birthDate, Time birthTime, String birthHospitalName, String birthID, List<userTakenVaccines> takenVaccinesList, List<userPendingVaccines> pendingVaccinesList) {
        super.setRole(role);
        super.setEmail(email);
        super.setPassword(password);
        BabyName = babyName;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.birthHospitalName = birthHospitalName;
        this.birthID = birthID;
        this.takenVaccinesList = takenVaccinesList;
        this.pendingVaccinesList = pendingVaccinesList;
    }

    public String getBabyName() {
        return BabyName;
    }

    public void setBabyName(String babyName) {
        BabyName = babyName;
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

    public String getBirthID() {
        return birthID;
    }

    public void setBirthID(String birthID) {
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
