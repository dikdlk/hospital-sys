package com.gjj.service;

import com.gjj.mapper.StaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zh
 * @Description TODO
 * @createTime 2026/6/2
 */
@Service
public class StaService {

    @Autowired
    private StaMapper staMapper;

    public Integer getHosCount() {
        return staMapper.getHosCount();
    }

    public Integer getPatCount() {
        return staMapper.getPatCount();
    }

    public List<Map<String, Integer>> getPatAge() {
        return staMapper.getPatAge();
    }

    public List<Map<String, Integer>> getHosPatCount() {
        return staMapper.getHosPatCount();
    }
}
