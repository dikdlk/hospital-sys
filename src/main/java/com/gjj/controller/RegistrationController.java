package com.gjj.controller;

import com.gjj.pojo.padoId;
import com.gjj.pojo.Registration;
import com.gjj.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gjj.pojo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @PostMapping("/register")
    public Result register(@RequestBody padoId  padoId){
       Integer patientId = padoId.getPatientId();
        Integer doctorId = padoId.getDoctorId();
        boolean success= registrationService.registerReg(patientId,doctorId);
        return new Result("200", "挂号成功", success) ;
}
@GetMapping("/list")
    public Result list(){
    List<Registration> registrations=registrationService.findAll();
    return new Result("200","",registrations) ;
}
@PutMapping("/cancel/{id}")
    public Result cancel(@PathVariable("id") Integer id){
     boolean success=registrationService.cancle(id);
     return new Result("200","",success) ;
}
@GetMapping("/listByPatient/{patientId}")
    public Result listByPatient(@PathVariable("patientId") Integer patientId){
        List<Registration> registrations =registrationService.findByPatient(patientId);
        return new Result("200","",registrations) ;
}
@GetMapping("/getById/{id}")
    public Result getById(@PathVariable("id") Integer id){
        Registration registration=registrationService.findById(id);
        return new Result("200","",registration) ;
}
@GetMapping("/listByDoctor/{doctorId}")
    public Result listByDoctor(@PathVariable("doctorId") Integer doctorId){
        List<Registration> registrations=registrationService.findBydoctor(doctorId);
        return new Result("200","",registrations) ;
}
}
