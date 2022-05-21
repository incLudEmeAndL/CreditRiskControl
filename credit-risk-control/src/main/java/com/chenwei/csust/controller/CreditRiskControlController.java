package com.chenwei.csust.controller;

import com.chenwei.csust.common.constante.ResponseStatusConst;
import com.chenwei.csust.common.entity.ApiResult;
import com.chenwei.csust.model.PredictionSearchEntity;
import com.chenwei.csust.spark.service.CreditRiskControlService;
import com.chenwei.csust.util.ApiResultHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 风向控制接口
 */
@RestController
@RequestMapping("/credit-risk-control")
@Api(value = "datasource")
public class CreditRiskControlController {
    private Logger logger = LoggerFactory.getLogger(CreditRiskControlController.class);

    @Autowired
    CreditRiskControlService creditRiskControlService;

    /**
     * 使用模型进行风险预测
     * @return
     */
    @RequestMapping(value = "/prediction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "预测个人风险", notes = "预测个人风险")
    public ApiResult creditRiskControl(@RequestBody PredictionSearchEntity searchModel,
                                       String staffId) {
        logger.info(staffId);
        if (searchModel.checkData()) {
            Object object = creditRiskControlService.riskControl(searchModel);
            logger.info("[CreditRiskControlController]-creditRiskControl success: 成功返回预测模型");
            return ApiResultHandler.success(object);
        } else {
            logger.info("[CreditRiskControlController]-creditRiskControl fail: 预测模型失败");
            return ApiResultHandler.buildApiResult(ResponseStatusConst.CodeBizError, "风险预测异常", null);
        }
    }
}
