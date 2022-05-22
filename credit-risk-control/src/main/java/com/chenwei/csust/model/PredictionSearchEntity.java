package com.chenwei.csust.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PredictionSearchEntity {
    /**
     * 通过Id获取模型
     */
    @ApiModelProperty("模型Id")
    public int modelId;

    /**
     * 风险预测接收参数
     */
    @ApiModelProperty("特征数据")
    public Double[] searchData;

    // 校验
    public boolean checkData() {
        if (searchData.length != 81 || modelId <= 0) {
            return false;
        }
        return true;
    }
}
