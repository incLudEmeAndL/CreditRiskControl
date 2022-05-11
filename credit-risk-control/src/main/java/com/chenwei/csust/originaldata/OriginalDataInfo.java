package com.chenwei.csust.originaldata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tech.tablesaw.api.Table;

import java.io.IOException;

/**
 * 原始数据探索
 */
@Component
public class OriginalDataInfo {
    private static final Logger logger = LoggerFactory.getLogger(OriginalDataInfo.class);
    @Value("${originalDataUrl}")
    private String url;

    /**
     * 原始数据信息探索
     */
    @Bean
    public void readData(){
        try {
            //
            System.out.println(url);
            Table table = Table.read().csv(url);
            // 列信息
            System.out.println(table.columnNames());
            // table结构
            System.out.println(table.structure());
            // 输出行数
            System.out.println(table.shape());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
