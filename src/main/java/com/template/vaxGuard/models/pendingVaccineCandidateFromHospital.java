package com.template.vaxGuard.models;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class pendingVaccineCandidateFromHospital extends vaccineCandidate{

    public pendingVaccineCandidateFromHospital(String babyName, String email, Date birthDate, Time birthTime, String birthHospitalName) {
        this.setBabyName(babyName);
        this.setEmail(email);
        this.setBirthDate(birthDate);
        this.setBirthTime(birthTime);
        this.setBirthHospitalName(birthHospitalName);
    }


}