package com.gjj.controller;


import com.gjj.pojo.Hospital;
import com.gjj.pojo.Result;
import com.gjj.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hos")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/insertHos")
    public Result insertHospital(@RequestBody Hospital hospital){
      boolean seccuss=  hospitalService.insertHospital(hospital);
        return new Result("204","添加成功",seccuss);
    }
    @GetMapping("/findHosList")
    public Result findHospitalList(){
        List<Hospital> hospitals=hospitalService.findHospital();
        return new Result("200","获取数据成功",hospitals);
    }
    @DeleteMapping("/deleteById")
    public Result deleteHospital(@RequestParam Integer id)
    {
        boolean success=hospitalService.deleteHospital(id);
        return new Result("202","删除成功",success);

    }
@DeleteMapping("/deleteByIds")
    public Result deleteHospitals(@RequestBody List<Integer> ids)
    {
        boolean success=hospitalService.deleteHospitals(ids);
        return new Result("203","删除成功",success);
    }
    @PutMapping("/updateHos")
    public Result UpdataHospital(@RequestBody Hospital hospital)
    {
boolean success=hospitalService.updateHospital(hospital);
return new Result("201","更新成功",success);
    }
@GetMapping("/findHosByLikeName")
    public Result findHospitalByLikeName(@RequestParam String hosName,
                                         @RequestParam String hosLevel){
List<Hospital> hospitals=hospitalService.findHospitalByName(hosName,hosLevel);
return new Result("205","查询成功",hospitals);

}

}
