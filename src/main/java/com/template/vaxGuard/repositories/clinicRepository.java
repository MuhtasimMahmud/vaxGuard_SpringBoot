package com.template.vaxGuard.repositories;

import com.template.vaxGuard.models.clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface clinicRepository extends JpaRepository<clinic, String> {

    public clinic findByEmail(String email);

    public List<clinic> findAll();

}
