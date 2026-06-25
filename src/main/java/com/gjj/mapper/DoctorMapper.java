package com.gjj.mapper;

import com.gjj.pojo.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zh
 * @Description 医生数据访问接口
 * @createTime 2026/6/4
 */
public interface DoctorMapper {
    // 添加医生
    void insertDoctor(@Param("doctor") Doctor doctor);

    // 修改医生
    void updateDoctor(@Param("doctor") Doctor doctor);

    // 删除医生
    void deleteById(@Param("id") Integer id);

    // 批量删除医生
    void deleteByIds(@Param("ids") List<Integer> ids);

    // 查询所有医生
    List<Doctor> findDoctorList();

    // 根据ID查询医生
    Doctor findDoctorById(@Param("id") Integer id);

    // 模糊查询医生（按姓名、科室、职称）
    List<Doctor> findDoctorByLike(@Param("doctorName") String doctorName, 
                                   @Param("department") String department, 
                                   @Param("doctorTitle") String doctorTitle);
}