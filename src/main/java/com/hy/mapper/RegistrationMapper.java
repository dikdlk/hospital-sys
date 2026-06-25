package com.hy.mapper;

import com.hy.pojo.Registration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zh
 * @Description 挂号数据访问接口
 * @createTime 2026/6/4
 */
public interface RegistrationMapper {
    // 添加挂号记录
    void insertRegistration(@Param("registration") Registration registration);

    // 更新挂号状态
    void updateStatus(@Param("id") Integer id, @Param("status") String status);

    // 根据ID查询挂号记录
    Registration findById(@Param("id") Integer id);

    // 根据患者ID查询挂号记录
    List<Registration> findByPatientId(@Param("patientId") Integer patientId);

    // 根据医生ID查询挂号记录
    List<Registration> findByDoctorId(@Param("doctorId") Integer doctorId);

    // 查询所有挂号记录
    List<Registration> findAll();

    // 根据ID删除挂号记录
    void deleteById(@Param("id") Integer id);
}