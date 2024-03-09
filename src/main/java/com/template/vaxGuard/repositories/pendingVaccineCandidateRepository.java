package com.template.vaxGuard.repositories;

import com.template.vaxGuard.models.pendingVaccineCandidateFromHospital;
import com.template.vaxGuard.models.vaccineCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pendingVaccineCandidateRepository extends JpaRepository<vaccineCandidate, String> {

    public pendingVaccineCandidateFromHospital findByEmail(String email);


}
