package com.hy.mapper;

import com.hy.pojo.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zh
 * @Description 患者数据访问接口
 * @createTime 2026/6/4
 */
public interface PatientMapper {
    // 添加患者
    void insertPatient(@Param("patient") Patient patient);

    // 修改患者
    void updatePatient(@Param("patient") Patient patient);

    // 删除患者
    void deleteById(@Param("id") Integer id);

    // 批量删除患者
    void deleteByIds(@Param("ids") List<Integer> ids);

    // 查询所有患者
    List<Patient> findPatientList();

    // 根据ID查询患者
    Patient findPatientById(@Param("id") Integer id);

    // 根据医院ID查询患者
    List<Patient> findPatientByHospitalId(@Param("hospitalId") Integer hospitalId);

    // 模糊查询患者（按姓名、手机号、身份证号）
    List<Patient> findPatientByLike(@Param("patientName") String patientName, 
                                     @Param("patientPhone") String patientPhone, 
                                     @Param("patientIdcard") String patientIdcard);
}