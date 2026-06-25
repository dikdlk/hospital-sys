package com.gjj.pojo;

/**
 * @author zh
 * @Description 医生实体类
 * @createTime 2026/6/4
 */
public class Doctor {
    private Integer id;
    private Integer hospitalId;
    private String doctorName;
    private String doctorTitle;
    private String department;
    private String doctorCode;
    private String phone;
    private String status;
    private Integer numCount;

    public Doctor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumCount() {
        return numCount;
    }

    public void setNumCount(Integer numCount) {
        this.numCount = numCount;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", hospitalId=" + hospitalId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorTitle='" + doctorTitle + '\'' +
                ", department='" + department + '\'' +
                ", doctorCode='" + doctorCode + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", numCount=" + numCount +
                '}';
    }
}