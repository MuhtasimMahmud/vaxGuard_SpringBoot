package com.template.vaxGuard.repositories;

import com.template.vaxGuard.models.pendingCandidateFromHospital;
import com.template.vaxGuard.models.vaccineCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pendingCandidateFromHospitalRepository extends JpaRepository<pendingCandidateFromHospital, Integer> {

    public pendingCandidateFromHospital findByEmail(String email);


}
