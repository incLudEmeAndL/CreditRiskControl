package com.chenwei.csust.controller;

import com.chenwei.csust.common.constante.ResponseStatusConst;
import com.chenwei.csust.common.entity.ApiResult;
import com.chenwei.csust.model.BaseParam;
import com.chenwei.csust.model.ModelInfoDto;
import com.chenwei.csust.service.ModelInfoService;
import com.chenwei.csust.util.ApiResultHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 模型信息接口
 */
@Controller
@RequestMapping("modelInfo")
@Api(value = "datasource")
public class ModelInfoController {
    private Logger logger = LoggerFactory.getLogger(ModelInfoController.class);

    @Autowired
    ModelInfoService modelInfoService;

    /**
     * 分页获取模型信息
     * @param baseParam
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取模型信息列表", notes = "获取模型信息列表")
    public ApiResult getModelInfoList(BaseParam baseParam, String staffId) {
        logger.info(staffId);
        PageHelper.startPage(baseParam.getPageNo(), baseParam.getPageSize());

        List<ModelInfoDto> listModelInfo = modelInfoService.getList();

        PageInfo<ModelInfoDto> pageInfo = new PageInfo<>(listModelInfo);
        pageInfo.setList(null);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("listModelInfo", listModelInfo);
        hashMap.put("pager", pageInfo);

        return ApiResultHandler.success(hashMap);
    }


    @RequestMapping(value = "/maxAccuracyModel", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取最高评分模型", notes = "获取最高评分模型")
    public ApiResult maxAccuracyModelInfo(String modelType, String staffId) {
        logger.info(staffId);
        if (staffId != null || !staffId.isEmpty()) {
            ModelInfoDto modelInfoDto = modelInfoService.getMaxAccuracyModel(modelType);
            logger.info("[ModelInfoController]-maxAccuracyModelInfo info :成功返回最高评分模型");
            return ApiResultHandler.success(modelInfoDto);
        } else {
            logger.error("[ModelInfoController]-maxAccuracyModelInfo error :没有权限访问");
            return ApiResultHandler.buildApiResult(ResponseStatusConst.CodeAuthorityError, "没有权限", null);
        }
    }
}
