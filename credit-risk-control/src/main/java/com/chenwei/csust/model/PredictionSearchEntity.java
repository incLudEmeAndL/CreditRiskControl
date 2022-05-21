package com.chenwei.csust.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PredictionSearchEntity {
    /**
     * 风险预测接收参数
     */
    @ApiModelProperty("请求参数")
    public Double[] searchData;

    // 校验
    public boolean checkData() {
        if (searchData.length != 81) {
            return false;
        }
        return true;
    }
}
