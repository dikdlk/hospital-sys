package com.gjj.mapper;

import com.gjj.pojo.Hospital;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zh
 * @Description TODO
 * @createTime 2026/5/28
 */
public interface HospitalMapper {
    List<Hospital> findHosList();

    Hospital selectHosByName(@Param("hosName") String hospitalName);

    void insertHos(@Param("hos") Hospital hospital);

    void deleteById(@Param("id") Integer id);

    void deleteByIds(@Param("ids") List<Integer> ids);

    void updateHos(@Param("hos") Hospital hospital);

    List<Hospital> findHosByLikeName(@Param("hosName") String hosName, @Param("hosLevel") String hosLevel);

}
