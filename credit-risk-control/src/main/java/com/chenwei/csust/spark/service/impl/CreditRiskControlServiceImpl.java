package com.chenwei.csust.spark.service.impl;

import com.chenwei.csust.spark.config.HDFSConfig;
import com.chenwei.csust.spark.config.SparkConfig;
import com.chenwei.csust.model.PredictionSearchEntity;
import com.chenwei.csust.spark.model.RiskControlModel;
import com.chenwei.csust.spark.service.CreditRiskControlService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.classification.RandomForestClassificationModel;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.tuning.CrossValidatorModel;
import org.apache.spark.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CreditRiskControlServiceImpl implements CreditRiskControlService {
    private Logger logger = LoggerFactory.getLogger(CreditRiskControlServiceImpl.class);
    @Autowired
    HDFSConfig hdfsConfig;
    @Autowired
    SparkConfig sparkConfig;

    @Override
    public List<Dataset<Row>> riskControl(PredictionSearchEntity searchData){
        List<Dataset<Row>> resultList = new ArrayList<>();

        Configuration configuration = sparkConfig.getHadoopConfiguration();
        SparkSession sparkSession = sparkConfig.getSparkSession();

        RiskControlModel modelData = new RiskControlModel(searchData.searchData[0], searchData.searchData[1],
                searchData.searchData[2], searchData.searchData[3],
                searchData.searchData[4], searchData.searchData[5], searchData.searchData[6], searchData.searchData[7],
                searchData.searchData[8], searchData.searchData[9], searchData.searchData[10], searchData.searchData[11],
                searchData.searchData[12], searchData.searchData[13], searchData.searchData[14], searchData.searchData[15],
                searchData.searchData[16], searchData.searchData[17], searchData.searchData[18], searchData.searchData[19],
                searchData.searchData[20], searchData.searchData[21], searchData.searchData[22], searchData.searchData[23],
                searchData.searchData[24], searchData.searchData[25], searchData.searchData[26], searchData.searchData[27],
                searchData.searchData[28], searchData.searchData[29], searchData.searchData[30], searchData.searchData[31],
                searchData.searchData[32], searchData.searchData[33], searchData.searchData[34], searchData.searchData[35],
                searchData.searchData[36], searchData.searchData[37], searchData.searchData[38], searchData.searchData[39],
                searchData.searchData[40], searchData.searchData[41], searchData.searchData[42], searchData.searchData[43],
                searchData.searchData[44],
                searchData.searchData[45], searchData.searchData[46], searchData.searchData[47], searchData.searchData[48],
                searchData.searchData[49], searchData.searchData[50], searchData.searchData[51], searchData.searchData[52],
                searchData.searchData[53], searchData.searchData[54], searchData.searchData[55], searchData.searchData[56],
                searchData.searchData[57], searchData.searchData[58], searchData.searchData[59], searchData.searchData[60],
                searchData.searchData[61], searchData.searchData[62], searchData.searchData[63], searchData.searchData[64],
                searchData.searchData[65], searchData.searchData[66], searchData.searchData[67], searchData.searchData[68],
                searchData.searchData[69], searchData.searchData[70], searchData.searchData[71], searchData.searchData[72],
                searchData.searchData[73], searchData.searchData[74], searchData.searchData[75], searchData.searchData[76],
                searchData.searchData[77], searchData.searchData[78], searchData.searchData[79], searchData.searchData[80]);

        // Dataset<Row> searchDataFrame = sparkSession.createDataFrame(modelList, RiskControlModel.class).cache();
        // searchDataFrame.show();

        String[] featureCols = new String[]{
            "loan_amnt", "term", "int_rate", "installment", "annual_inc",
            "dti", "earliest_cr_line", "open_acc", "pub_rec",
            "revol_bal", "revol_util", "total_acc", "mort_acc",
            "pub_rec_bankruptcies", "sub_grade_A2", "sub_grade_A3", "sub_grade_A4",
            "sub_grade_A5", "sub_grade_B1", "sub_grade_B2", "sub_grade_B3",
            "sub_grade_B4", "sub_grade_B5", "sub_grade_C1", "sub_grade_C2",
            "sub_grade_C3", "sub_grade_C4", "sub_grade_C5", "sub_grade_D1",
            "sub_grade_D2", "sub_grade_D3", "sub_grade_D4", "sub_grade_D5",
            "sub_grade_E1", "sub_grade_E2", "sub_grade_E3", "sub_grade_E4",
            "sub_grade_E5", "sub_grade_F1", "sub_grade_F2", "sub_grade_F3",
            "sub_grade_F4", "sub_grade_F5", "sub_grade_G1", "sub_grade_G2",
            "sub_grade_G3", "sub_grade_G4", "sub_grade_G5",
            "verification_status_Source_Verified", "verification_status_Verified",
            "purpose_credit_card", "purpose_debt_consolidation",
            "purpose_educational", "purpose_home_improvement", "purpose_house",
            "purpose_major_purchase", "purpose_medical", "purpose_moving",
            "purpose_other", "purpose_renewable_energy", "purpose_small_business",
            "purpose_vacation", "purpose_wedding", "initial_list_status_w",
            "application_type_INDIVIDUAL", "application_type_JOINT",
            "home_ownership_MORTGAGE", "home_ownership_NONE",
            "home_ownership_OTHER", "home_ownership_OWN", "home_ownership_RENT",
            "zip_code_05113", "zip_code_11650", "zip_code_22690", "zip_code_29597",
            "zip_code_30723", "zip_code_48052", "zip_code_70466", "zip_code_86630",
            "zip_code_93700"
        };


        Encoder<RiskControlModel> modelEncoder = Encoders.bean(RiskControlModel.class);
        Dataset<RiskControlModel> dataset = sparkSession.createDataset(Collections.singletonList(modelData), modelEncoder);
        VectorAssembler assembler = new VectorAssembler().setInputCols(featureCols).setOutputCol("features");
        Dataset<Row> transform = assembler.transform(dataset);

        StringIndexer labelIndexer = new StringIndexer().setInputCol("loan_status").setOutputCol("label");
        Dataset<Row> transformDF = labelIndexer.fit(transform).transform(transform);

        // transformDF.show();

        try {
            // 从HDFS读取模型
            FileSystem fileSystem = FileSystem.get(configuration);
            Path path1 = new Path(hdfsConfig.getPath()+hdfsConfig.getModel1());
            Path path2 = new Path(hdfsConfig.getPath()+hdfsConfig.getModel2());
            ObjectInputStream objectInputStream1 = new ObjectInputStream(new FSDataInputStream(fileSystem.open(path1)));
            ObjectInputStream objectInputStream2 = new ObjectInputStream(new FSDataInputStream(fileSystem.open(path2)));

            RandomForestClassificationModel randomForestModel = (RandomForestClassificationModel)objectInputStream1.readObject();
            CrossValidatorModel pipelineRandomForestModel = (CrossValidatorModel)objectInputStream2.readObject();
            // System.out.println(randomForestModel);
            // System.out.println(pipelineRandomForestModel);

            // randomForestModel
            // 预测
            Dataset<Row> prediction = randomForestModel.transform(transformDF);
            prediction.show();

            // pipelineRandomForestModel
            // 预测
            Dataset<Row> pipelinePrediction = pipelineRandomForestModel.transform(transformDF);
            pipelinePrediction.show();

            // 结果评估
            Dataset<Row> selectData = prediction.select("label", "rawPrediction", "probability", "prediction");
            System.out.println(selectData.columns().toString());
            selectData.show();
            resultList.add(selectData);

            // pipeline模型评估
            Dataset<Row> pipelineSelectData = pipelinePrediction.select("label", "rawPrediction", "probability", "prediction");
            // System.out.println(selectData.columns().toString());
            pipelineSelectData.show();
            resultList.add(pipelineSelectData);

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return resultList;
    }
}
