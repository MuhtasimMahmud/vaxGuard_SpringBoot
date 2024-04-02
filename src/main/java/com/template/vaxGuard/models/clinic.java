package com.template.vaxGuard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class clinic{

    private String name;
    @Column(unique = true)
    private String registrationNumber;
    @Id
    private String email;

    @OneToMany
    private List<clinicVaccines> vaccinesList;
    @OneToMany
    private List<vaccineCandidate> records;
    @OneToMany
    private List<vaccineCandidate> requests;

    private String address;


    public clinic(){
        //Default Constructor
    }

    public clinic(String name, String registrationNumber, String email, List<clinicVaccines> vaccinesList, List<vaccineCandidate> records, List<vaccineCandidate> requests) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.email = email;
        this.vaccinesList = vaccinesList;
        this.records = records;
        this.requests = requests;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<clinicVaccines> getVaccinesList() {
        return vaccinesList;
    }

    public void setVaccinesList(List<clinicVaccines> vaccinesList) {
        this.vaccinesList = vaccinesList;
    }

    public List<vaccineCandidate> getRecords() {
        return records;
    }

    public void setRecords(List<vaccineCandidate> records) {
        this.records = records;
    }

    public List<vaccineCandidate> getRequests() {
        return requests;
    }

    public void setRequests(List<vaccineCandidate> requests) {
        this.requests = requests;
    }
}
