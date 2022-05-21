package com.chenwei.csust.spark.service;

import com.chenwei.csust.model.PredictionSearchEntity;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;

public interface CreditRiskControlService {
    List<Dataset<Row>> riskControl(PredictionSearchEntity searchData);
}
