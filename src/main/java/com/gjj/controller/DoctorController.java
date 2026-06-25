package com.gjj.controller;


import com.gjj.pojo.Doctor;
import com.gjj.pojo.Result;
import com.gjj.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

@GetMapping("/list")
    public Result list(){
    List<Doctor> doctors=doctorService.findDoctor();
    return new Result("200","success",doctors);
}
@PostMapping("/add")
    public Result addDoctor(@RequestBody Doctor doctor){
   boolean success=doctorService.insertDoctor(doctor);
   return new Result("200","success",success);
}
@PutMapping("/update")
    public Result upsateDoctor(@RequestBody Doctor doctor){
    boolean success=doctorService.updateDoctor(doctor);
    return new Result("200","success",success);
}
@DeleteMapping("/delete/{id}")
    public Result deleteDoctor(@PathVariable Integer id){
    boolean success=doctorService.deleteDoctor(id);
    return new Result("200","success",success);

}
@DeleteMapping("/deleteByIds")
    public Result deleteDoctorByIds(@RequestBody List<Integer> ids){
    boolean success=doctorService.deleteDoctor(ids);
    return new Result("200","success",success);
}
@GetMapping("getById/{id}")
    public Result getDoctorById(@PathVariable Integer id){
    Doctor doctor=doctorService.findDoctorById(id);
            return  new Result("200","success",doctor);
}
@GetMapping("/listByCondition")
    public Result listByCondition(@RequestParam  String doctorName,
                                  @RequestParam String department,
                                 @RequestParam String doctorTitle){
    List<Doctor> doctors=doctorService.findDoctorByName(doctorName,department,doctorTitle);
    return new Result("200","success",doctors);
}
}
