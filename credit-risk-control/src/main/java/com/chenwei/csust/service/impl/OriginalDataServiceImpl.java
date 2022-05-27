package com.chenwei.csust.service.impl;

import com.chenwei.csust.entity.LendingClubLoan;
import com.chenwei.csust.mapper.LendingClubLoanMapper;
import com.chenwei.csust.service.OriginalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginalDataServiceImpl implements OriginalDataService {
    @Autowired
    LendingClubLoanMapper lendingClubLoanMapper;

    @Override
    public List<LendingClubLoan> getList() {
        return lendingClubLoanMapper.getList();
    }
}
