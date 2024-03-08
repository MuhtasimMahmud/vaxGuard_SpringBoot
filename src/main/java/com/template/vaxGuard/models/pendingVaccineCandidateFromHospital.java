package com.template.vaxGuard.models;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class pendingVaccineCandidateFromHospital extends vaccineCandidate{


    public pendingVaccineCandidateFromHospital(String babyName, String email, Date birthDate, Time birthTime, String birthHospitalName) {
        super.setBabyName(babyName);
        super.setEmail(email);
        super.setBirthDate(birthDate);
        super.setBirthTime(birthTime);
        super.setBirthHospitalName(birthHospitalName);
    }
}