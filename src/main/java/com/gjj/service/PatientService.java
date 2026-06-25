package com.gjj.service;

import com.gjj.mapper.PatientMapper;
import com.gjj.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientMapper patientMapper;
public List<Patient> findPatientList(){

    List<Patient> patientList = patientMapper.findPatientList();
    return patientList;
}
    public boolean insertpatient(Patient patient){
        patientMapper.insertPatient(patient);
        return true;
    }
    public boolean deletepatient(Integer id){
      patientMapper.deleteById(id);
        return true;
    }
    public boolean deletepatients(List<Integer> ids){
        patientMapper.deleteByIds(ids);
        return true;
    }
    public boolean updatepatient(Patient patient){
        patientMapper.updatePatient(patient);
        return true;
    }
    public List<Patient> findHospitalByName(Integer name){
        List<Patient> patients=patientMapper.findPatientByHospitalId(name);
        return patients;
    }
    public List<Patient> findbyCondition(String name,String phone,String card){
    List<Patient> patients=patientMapper.findPatientByLike(name,phone,card);
return patients;
    }
public Patient findPatientById(Integer id){
    Patient patient=patientMapper.findPatientById(id);
    return patient;
}


}
