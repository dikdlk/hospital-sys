package com.gjj.service;


import com.gjj.mapper.DoctorMapper;
import com.gjj.pojo.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    public   boolean insertDoctor(Doctor doctor){
        doctorMapper.insertDoctor(doctor);
        return true;

    }
    public List<Doctor> findDoctor(){
        List<Doctor> doctors=doctorMapper.findDoctorList();
        return doctors;

    }
    public boolean deleteDoctor(Integer id){
  doctorMapper.deleteById(id);
        return true;
    }
    public boolean deleteDoctor(List<Integer> ids){
        doctorMapper.deleteByIds(ids);
        return true;
    }
    public boolean updateDoctor(Doctor doctor){
        doctorMapper.updateDoctor(doctor);
        return true;
    }
    public List<Doctor> findDoctorByName(String doctorName,
                                             String department,
                                             String doctorTitle){
        List<Doctor> doctors=doctorMapper.findDoctorByLike(doctorName,department,doctorTitle);
        return doctors;
    }
    public Doctor findDoctorById(Integer id){
        Doctor doctor=doctorMapper.findDoctorById(id);
        return doctor;
    }
}
