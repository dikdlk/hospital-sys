package com.hy.pojo;

/**
 * @author zh
 * @Description TODO
 * @createTime 2026/5/28
 */
public class Hospital {
    private Integer id;
    private String hospitalName;
    private String hospitalType;
    private String hospitalLevel;
    private String hospitalCode;
    private String hospitalAddress;
    private String hospitalStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalStatus() {
        return hospitalStatus;
    }

    public void setHospitalStatus(String hospitalStatus) {
        this.hospitalStatus = hospitalStatus;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalType='" + hospitalType + '\'' +
                ", hospitalLevel='" + hospitalLevel + '\'' +
                ", hospitalCode='" + hospitalCode + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                ", hospitalStatus='" + hospitalStatus + '\'' +
                '}';
    }
}
