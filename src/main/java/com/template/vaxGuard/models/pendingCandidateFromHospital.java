package com.template.vaxGuard.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class pendingCandidateFromHospital{

    private String babyName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime birthTime;
    private String birthHospitalName;
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int birthID;

    public pendingCandidateFromHospital() {

    }

    public pendingCandidateFromHospital(String babyName, LocalDate birthDate, LocalTime birthTime, String birthHospitalName, String email, int birthID) {
        this.babyName = babyName;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.birthHospitalName = birthHospitalName;
        this.email = email;
        this.birthID = birthID;
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
}