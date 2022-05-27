package com.chenwei.csust.controller;

import com.chenwei.csust.common.entity.ApiResult;
import com.chenwei.csust.entity.ModelData;
import com.chenwei.csust.model.BaseParam;
import com.chenwei.csust.service.ModelDataService;
import com.chenwei.csust.service.OriginalDataService;
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

@Controller
@RequestMapping(value = "/modelData")
@Api(value = "datasource")
public class ModelDataController {
    private Logger logger = LoggerFactory.getLogger(ModelDataController.class);

    @Autowired
    ModelDataService modelDataService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取模型训练数据列表", notes = "获取模型训练数据列表")
    public ApiResult getOriginalDataList(BaseParam baseParam, String staffId){
        logger.info(staffId);

        PageHelper.startPage(baseParam.getPageNo(), baseParam.getPageSize());

        List<ModelData> listModelInfo = modelDataService.getList();

        PageInfo<ModelData> pageInfo = new PageInfo<>(listModelInfo);
        pageInfo.setList(null);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("listOriginalData", listModelInfo);
        hashMap.put("pager", pageInfo);

        return ApiResultHandler.success(hashMap);
    }
}
