package com.chenwei.csust.service.impl;

import com.chenwei.csust.entity.ModelData;
import com.chenwei.csust.mapper.ModelDataMapper;
import com.chenwei.csust.service.ModelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelDataServiceImpl implements ModelDataService {
    @Autowired
    ModelDataMapper modelDataMapper;
    @Override
    public List<ModelData> getList() {
        return modelDataMapper.getList();
    }
}
