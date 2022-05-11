package com.chenwei.csust.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Value("${originaldata.url}")
    private String OriginalDataUrl;
}
