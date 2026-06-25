package com.hy.pojo;

import java.util.Date;

/**
 * @author zh
 * @Description 患者实体类
 * @createTime 2026/6/4
 */
public class Patient {
    private Integer id;
    private String patientName;
    private String patientPhone;
    private String patientIdcard;
    private String patientGender;
    private Integer patientAge;
    private Date patientBirthday;
    private String patientAddress;
    private String patientBloodType;
    private String patientAllergy;
    private Integer hospitalId;

    public Patient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientIdcard() {
        return patientIdcard;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public Date getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(Date patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientBloodType() {
        return patientBloodType;
    }

    public void setPatientBloodType(String patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    public String getPatientAllergy() {
        return patientAllergy;
    }

    public void setPatientAllergy(String patientAllergy) {
        this.patientAllergy = patientAllergy;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", patientPhone='" + patientPhone + '\'' +
                ", patientIdcard='" + patientIdcard + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientAge=" + patientAge +
                ", patientBirthday=" + patientBirthday +
                ", patientAddress='" + patientAddress + '\'' +
                ", patientBloodType='" + patientBloodType + '\'' +
                ", patientAllergy='" + patientAllergy + '\'' +
                ", hospitalId=" + hospitalId +
                '}';
    }
}