package com.template.vaxGuard.repositories;

import com.template.vaxGuard.models.clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface clinicRepository extends JpaRepository<clinic, String> {

    public clinic findByEmail(String email);
}
