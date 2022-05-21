package com.chenwei.csust.service.impl;

import com.chenwei.csust.entity.OriginalData;
import com.chenwei.csust.mapper.OriginalDataMapper;
import com.chenwei.csust.service.OriginalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginalDataServiceImpl implements OriginalDataService {
    @Autowired
    OriginalDataMapper originalDataMapper;

    @Override
    public List<OriginalData> getList() {
        return originalDataMapper.getList();
    }
}
