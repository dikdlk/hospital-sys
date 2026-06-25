package com.gjj.service;

import com.gjj.mapper.HospitalMapper;
import com.gjj.pojo.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    private HospitalMapper hospitalMapper;

  public   boolean insertHospital(Hospital hospital){
   hospitalMapper.insertHos(hospital);
   return true;
    }
    public List<Hospital>  findHospital(){
      List<Hospital> hospitals=hospitalMapper.findHosList();
      return hospitals;

    }
    public boolean deleteHospital(Integer id){
      hospitalMapper.deleteById(id);
      return true;
    }
    public boolean deleteHospitals(List<Integer> ids){
      hospitalMapper.deleteByIds(ids);
      return true;
    }
    public boolean updateHospital(Hospital hospital){
      hospitalMapper.updateHos(hospital);
      return true;
    }
    public List<Hospital> findHospitalByName(String name,String level){
      List<Hospital> hospitals=hospitalMapper.findHosByLikeName(name,level);
      return hospitals;
    }
}


