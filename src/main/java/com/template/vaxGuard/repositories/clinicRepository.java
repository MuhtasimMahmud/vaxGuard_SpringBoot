package com.template.vaxGuard.repositories;

import com.template.vaxGuard.models.clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface clinicRepository extends JpaRepository<clinic, String> {

    public clinic findByEmail(String email);

    public List<clinic> findAll();

    @Query("select c from clinic c where c.address = :address")
    public List<clinic> findAllByAddress(String address);

}
