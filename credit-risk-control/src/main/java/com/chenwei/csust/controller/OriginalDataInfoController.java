package com.chenwei.csust.controller;

import com.chenwei.csust.common.constante.ResponseStatusConst;
import com.chenwei.csust.common.entity.ApiResult;
import com.chenwei.csust.entity.LendingClubLoan;
import com.chenwei.csust.model.BaseParam;
import com.chenwei.csust.service.OriginalDataInfoService;
import com.chenwei.csust.service.OriginalDataService;
import com.chenwei.csust.util.ApiResultHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 原始数据探索
 */
@RestController
@RequestMapping("/originalData")
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
    @ApiOperation(value = "获取原始数据列信息", notes = "获取原始数据列信息")
    @ApiImplicitParam(paramType = "query", name = "staff Id", value = "用户id", required = true, dataType = "String")
    public ApiResult getColumnNames() {
        Object object = originalDataInfoService.getColumnNames();
        if (object != null) {
            logger.info("[OriginalDataInfoController]-getColumnNames success: 成功返回数据列信息");
            return ApiResultHandler.success(object);
        } else {
            logger.info("[OriginalDataInfoController]-getColumnNames fail: 获取列信息异常");
            return ApiResultHandler.buildApiResult(ResponseStatusConst.CodeBizError, "获取列信息异常", null);
        }
    }

    @Autowired
    OriginalDataService originalDataService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取原始数据列表", notes = "获取原始数据列表")
    public ApiResult getOriginalDataList(BaseParam baseParam, String staffId){
        logger.info(staffId);

        PageHelper.startPage(baseParam.getPageNo(), baseParam.getPageSize());

        List<LendingClubLoan> listModelInfo = originalDataService.getList();

        PageInfo<LendingClubLoan> pageInfo = new PageInfo<>(listModelInfo);
        pageInfo.setList(null);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("listOriginalData", listModelInfo);
        hashMap.put("pager", pageInfo);

        return ApiResultHandler.success(hashMap);
    }
}
