package com.chenwei.csust.mapper;

import com.chenwei.csust.entity.OriginalDataColumns;

public interface OriginalDataColumnsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OriginalDataColumns record);

    int insertSelective(OriginalDataColumns record);

    OriginalDataColumns selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OriginalDataColumns record);

    int updateByPrimaryKey(OriginalDataColumns record);
}