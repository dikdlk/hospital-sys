package com.hy.pojo;

import java.util.Date;

/**
 * @author zh
 * @Description 挂号实体类
 * @createTime 2026/6/4
 */
public class Registration {
    private Integer id;
    private Integer patientId;
    private Integer doctorId;
    private Integer hospitalId;
    private Date registrationDate;
    private String department;
    private String status;

    public Registration() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", hospitalId=" + hospitalId +
                ", registrationDate=" + registrationDate +
                ", department='" + department + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}