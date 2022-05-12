package com.chenwei.csust.controller;

import com.chenwei.csust.common.constante.ResponseStatusConst;
import com.chenwei.csust.config.DataSourceConfig;
import com.chenwei.csust.entity.ApiResult;
import com.chenwei.csust.service.OriginalDataInfoService;
import com.chenwei.csust.util.ApiResultHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.tablesaw.api.Table;

import java.io.IOException;

/**
 * 原始数据探索
 */
@RestController
@RequestMapping("/datasource")
@Api(value = "datasource")
public class OriginalDataInfoController {
    private static final Logger logger = LoggerFactory.getLogger(OriginalDataInfoController.class);

    @Autowired
    public OriginalDataInfoService originalDataInfoService;

    /**
     * 原始数据信息探索
     */
    @RequestMapping(value = "/columnNames", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value="获取原始数据列信息", notes="获取原始数据列信息")
    @ApiImplicitParam(paramType="query", name = "staff Id", value = "用户id", required = true, dataType = "String")
    public ApiResult getColumnNames(){
        Object object = originalDataInfoService.getColumnNames();
        if (object != null) {
            return ApiResultHandler.success(object);
        } else {
            return ApiResultHandler.buildApiResult(ResponseStatusConst.CodeBizError, "获取列信息异常", null);
        }
    }
}