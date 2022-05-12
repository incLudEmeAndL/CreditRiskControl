package com.chenwei.csust.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Value("${originalDataUrl}")
    private String OriginalDataUrl;

    public String getOriginalDataUrl() {
        return OriginalDataUrl;
    }
}
