package com.hy.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author zh
 * @Description TODO
 * @createTime 2026/6/2
 */
public interface StaMapper {
    Integer getHosCount();

    Integer getPatCount();

    List<Map<String, Integer>> getPatAge();

    List<Map<String, Integer>> getHosPatCount();
}
