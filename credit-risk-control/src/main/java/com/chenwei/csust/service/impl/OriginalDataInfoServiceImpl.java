package com.chenwei.csust.service.impl;

import com.chenwei.csust.config.DataSourceConfig;
import com.chenwei.csust.service.OriginalDataInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.util.List;

@Service
public class OriginalDataInfoServiceImpl implements OriginalDataInfoService {

    private static final Logger logger = LoggerFactory.getLogger(OriginalDataInfoServiceImpl.class);

    @Autowired
    private DataSourceConfig dataSourceConfig;

    public Table readFile() {
        try {
            return Table.read().csv(dataSourceConfig.getOriginalDataUrl());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> getColumnNames() {
        if (readFile() != null) {
            return readFile().columnNames();
        } else {
            return null;
        }
    }
}
