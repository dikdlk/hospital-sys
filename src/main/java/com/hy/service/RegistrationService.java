package com.hy.service;

import com.hy.mapper.DoctorMapper;
import com.hy.mapper.PatientMapper;
import com.hy.mapper.RegistrationMapper;
import com.hy.pojo.Doctor;
import com.hy.pojo.Patient;
import com.hy.pojo.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
      registrationMapper.insertRegistration(registration);
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
