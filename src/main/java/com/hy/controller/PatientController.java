package com.hy.controller;

import com.hy.pojo.Patient;
import com.hy.pojo.Result;
import com.hy.service.DoctorService;
import com.hy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

@GetMapping("/list")
    public Result patientList(){
   List<Patient> patients= patientService.findPatientList();
   return new Result("200","success",patients);
}
@PostMapping("/add")
    public Result addPatient(@RequestBody Patient patient){
    boolean success=patientService.insertpatient(patient);

        return new Result("200","添加成功",success);

}
@PutMapping("/update")
    public Result updatePatient(@RequestBody Patient patient) {
    boolean success = patientService.updatepatient(patient);
    return new Result("200", "更新成功", success);
}
@DeleteMapping("/delete/{id}")
    public Result deletePatient(@PathVariable Integer id ) {
    boolean success=patientService.deletepatient(id);
    return new Result("200","删除成功",success);
}
@DeleteMapping("/deleteByIds")
    public Result deletePatientByIds(@RequestBody List<Integer> ids) {
    boolean success=patientService.deletepatients(ids);
    return new Result("200","删除成功",success);
}
@GetMapping("/getById")
    public Result findPatientById(@PathVariable Integer id) {
  Patient patient=patientService.findPatientById(id);
  return new Result("200","success",patient);
}
@GetMapping("/listByHospital/{hospitalId}")
    public Result findPatientByHospitalId(@PathVariable Integer hospitalId) {
    List<Patient> patients=patientService.findHospitalByName(hospitalId);
    return new Result("200","success",patients);
}
@GetMapping("/listByCondition")
    public Result findPatientByCondition(@RequestParam String patientName,
                                         @RequestParam String patientPhone ,
                                         @RequestParam String patientIdcard) {
    List<Patient> patients=patientService.findbyCondition(patientName,patientPhone,patientIdcard);
    return new Result("200","success",patients);
}

}
