package com.chenwei.csust.service;

import com.chenwei.csust.entity.ModelData;

import java.util.List;

public interface ModelDataService {
    /**
     * 获取原始数据列表
     * @return
     */
    List<ModelData> getList();
}
