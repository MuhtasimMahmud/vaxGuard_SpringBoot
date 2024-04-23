package com.template.vaxGuard.repositories;

import com.template.vaxGuard.models.vaccineCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface vaccineCandidateRepository extends JpaRepository<vaccineCandidate, Integer> {

    public vaccineCandidate findByEmail(String email);


}
