package com.chenwei.csust.service.impl;

import com.chenwei.csust.entity.ModelInfo;
import com.chenwei.csust.mapper.ModelInfoMapper;
import com.chenwei.csust.model.ModelInfoDto;
import com.chenwei.csust.service.ModelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelInfoServiceImpl implements ModelInfoService {
    @Autowired
    private ModelInfoMapper modelInfoMapper;

    /**
     * 获取模型列表集合
     * @return
     */
    @Override
    public List<ModelInfoDto> getList() {
        List<ModelInfo> modelInfoList =  modelInfoMapper.getList();
        List<ModelInfoDto> modelInfoDtoList = new ArrayList<>();
        for (ModelInfo modelInfo: modelInfoList
             ) {
            modelInfoDtoList.add(new ModelInfoDto(
                    modelInfo.getId(),
                    modelInfo.getName(),
                    modelInfo.getPath(),
                    modelInfo.getAccuracy(),
                    modelInfo.getMse(),
                    modelInfo.getMae(),
                    modelInfo.getRmsesquared(),
                    modelInfo.getType(),
                    modelInfo.getCreatedat()
            ));
        }
        return modelInfoDtoList;
    }

    @Override
    public ModelInfoDto getMaxAccuracyModel(String modelType) {
        ModelInfo modelInfo =  modelInfoMapper.getMaxAccuracyModelByType(modelType);
        if (modelInfo == null) {
            return null;
        }
        return new ModelInfoDto(
                modelInfo.getId(),
                modelInfo.getName(),
                modelInfo.getPath(),
                modelInfo.getAccuracy(),
                modelInfo.getMse(),
                modelInfo.getMae(),
                modelInfo.getRmsesquared(),
                modelInfo.getType(),
                modelInfo.getCreatedat()
        );
    }

    @Override
    public ModelInfoDto getModelById(Integer modelId) {
        ModelInfo modelInfo =  modelInfoMapper.selectByPrimaryKey(modelId);
        return new ModelInfoDto(
                modelInfo.getId(),
                modelInfo.getName(),
                modelInfo.getPath(),
                modelInfo.getAccuracy(),
                modelInfo.getMse(),
                modelInfo.getMae(),
                modelInfo.getRmsesquared(),
                modelInfo.getType(),
                modelInfo.getCreatedat()
        );
    }
}
