package com.chenwei.csust.mapper;

import com.chenwei.csust.entity.OriginalData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OriginalDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OriginalData record);

    int insertSelective(OriginalData record);

    OriginalData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OriginalData record);

    int updateByPrimaryKey(OriginalData record);

    List<OriginalData> getList();
}