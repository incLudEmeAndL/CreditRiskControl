package com.chenwei.csust.service;

import com.chenwei.csust.entity.LendingClubLoan;

import java.util.List;

public interface OriginalDataService {
    /**
     * 获取原始数据列表
     * @return
     */
    List<LendingClubLoan> getList();
}
