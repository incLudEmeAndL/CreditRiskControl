package com.chenwei.csust.mapper;

import com.chenwei.csust.entity.ModelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ModelInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelInfo record);

    int insertSelective(ModelInfo record);

    ModelInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelInfo record);

    int updateByPrimaryKey(ModelInfo record);

    List<ModelInfo> getList();

    ModelInfo getMaxAccuracyModelByType(String modelType);
}