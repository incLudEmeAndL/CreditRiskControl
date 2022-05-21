package com.chenwei.csust.service;

import com.chenwei.csust.model.ModelInfoDto;

import java.util.List;

public interface ModelInfoService {
    /**
     * 获取模型信息列表
     * @return
     */
    List<ModelInfoDto> getList();

    /**
     * 获取评分最高模型
     * @return
     */
    ModelInfoDto getMaxAccuracyModel(String modelType);
}
