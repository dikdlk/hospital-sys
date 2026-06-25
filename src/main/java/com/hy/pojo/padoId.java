package com.hy.pojo;

import lombok.Data;

@Data
public class padoId {
    private Integer patientId;
    private Integer doctorId;

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public padoId(Integer patientId) {
        this.patientId = patientId;
    }

}
