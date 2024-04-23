package com.template.vaxGuard.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class resetPasswordEntity {

    @Id
    private String email;
    private int resetCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getResetCode() {
        return resetCode;
    }

    public void setResetCode(int resetCode) {
        this.resetCode = resetCode;
    }
}
