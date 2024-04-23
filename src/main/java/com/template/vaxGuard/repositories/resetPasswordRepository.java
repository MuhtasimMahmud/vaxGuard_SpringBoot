package com.template.vaxGuard.repositories;

import com.template.vaxGuard.models.resetPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface resetPasswordRepository extends JpaRepository<resetPasswordEntity, String> {

    public resetPasswordEntity findByEmail(String email);


}
