package com.gjj.service;

import com.gjj.mapper.DoctorMapper;
import com.gjj.mapper.PatientMapper;
import com.gjj.mapper.RegistrationMapper;
import com.gjj.pojo.Doctor;
import com.gjj.pojo.Patient;
import com.gjj.pojo.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegistrationService {
  @Autowired
    private RegistrationMapper registrationMapper;
@Autowired
private DoctorMapper doctorMapper;
@Autowired
private PatientMapper patientMapper;
  public boolean registerReg(Integer patientId, Integer doctorId){
      Patient patient=patientMapper.findPatientById(patientId);
Doctor doctor=doctorMapper.findDoctorById(doctorId);
Registration registration=new Registration();
registration.setDoctorId(doctorId);
registration.setPatientId(patientId);
registration.setHospitalId(doctor.getHospitalId());
registration.setDepartment(doctor.getDepartment());
registration.setStatus("已挂号");
registration.setRegistrationDate(new Date());
doctor.setNumCount(doctor.getNumCount()+1);
      registrationMapper.insertRegistration(registration);
      doctorMapper.updateDoctor(doctor);
return true;
}
public List<Registration> findAll(){
      return registrationMapper.findAll();
}
public boolean cancle(Integer id)
{
    registrationMapper.deleteById(id);
    return true;
}
public List<Registration> findByPatient(Integer pain){

      return registrationMapper.findByPatientId(pain);

}
public Registration findById(Integer id){
      return registrationMapper.findById(id);
}
public List<Registration> findBydoctor(Integer doctorId){
      List<Registration> registrations=registrationMapper.findByDoctorId(doctorId);
      return registrations;
}
}
