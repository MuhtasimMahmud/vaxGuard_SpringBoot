package com.template.vaxGuard.models;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class pendingVaccineCandidateFromHospital extends vaccineCandidate{

    public pendingVaccineCandidateFromHospital(String email, String role, String password, String babyName, Date birthDate, Time birthTime, String birthHospitalName, String birthID, List<userTakenVaccines> takenVaccinesList, List<userPendingVaccines> pendingVaccinesList) {
        super(email, role, password, babyName, birthDate, birthTime, birthHospitalName, birthID, takenVaccinesList, pendingVaccinesList);
    }
}
