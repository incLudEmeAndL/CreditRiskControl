package com.chenwei.csust.app

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.util.UUID

import com.chenwei.csust.model.ModelInfo
import com.chenwei.csust.util.{MyKafkaUtils, MySQLUtils}
import org.apache.hadoop.fs.{FSDataInputStream, FSDataOutputStream, FileSystem, Path}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder}
import org.apache.spark.ml.{Pipeline, PipelineStage}
import org.apache.spark.mllib.evaluation.RegressionMetrics

object BaseDataApp {
  //创建一个Credit类，定义Credit类的属性
  case class Credit (
                     loan_amnt: Double, term: Double, int_rate: Double, installment: Double, annual_inc: Double,
                     loan_status: Double, dti: Double, earliest_cr_line: Double, open_acc: Double, pub_rec: Double,
                     revol_bal: Double, revol_util: Double, total_acc: Double, mort_acc: Double,
                     pub_rec_bankruptcies: Double, sub_grade_A2: Double, sub_grade_A3: Double, sub_grade_A4: Double,
                     sub_grade_A5: Double, sub_grade_B1: Double, sub_grade_B2: Double, sub_grade_B3: Double,
                     sub_grade_B4: Double, sub_grade_B5: Double, sub_grade_C1: Double, sub_grade_C2: Double,
                     sub_grade_C3: Double, sub_grade_C4: Double, sub_grade_C5: Double, sub_grade_D1: Double,
                     sub_grade_D2: Double, sub_grade_D3: Double, sub_grade_D4: Double, sub_grade_D5: Double,
                     sub_grade_E1: Double, sub_grade_E2: Double, sub_grade_E3: Double, sub_grade_E4: Double,
                     sub_grade_E5: Double, sub_grade_F1: Double, sub_grade_F2: Double, sub_grade_F3: Double,
                     sub_grade_F4: Double, sub_grade_F5: Double, sub_grade_G1: Double, sub_grade_G2: Double,
                     sub_grade_G3: Double, sub_grade_G4: Double, sub_grade_G5: Double,
                     verification_status_Source_Verified: Double, verification_status_Verified: Double,
                     purpose_credit_card: Double, purpose_debt_consolidation: Double, purpose_educational: Double,
                     purpose_home_improvement: Double, purpose_house: Double, purpose_major_purchase: Double,
                     purpose_medical: Double, purpose_moving: Double, purpose_other: Double,
                     purpose_renewable_energy: Double, purpose_small_business: Double, purpose_vacation: Double,
                     purpose_wedding: Double, initial_list_status_w: Double, application_type_INDIVIDUAL: Double,
                     application_type_JOINT: Double, home_ownership_MORTGAGE: Double, home_ownership_NONE: Double,
                     home_ownership_OTHER: Double, home_ownership_OWN: Double, home_ownership_RENT: Double,
                     zip_code_05113: Double, zip_code_11650: Double, zip_code_22690: Double, zip_code_29597: Double,
                     zip_code_30723: Double, zip_code_48052: Double, zip_code_70466: Double, zip_code_86630: Double,
                     zip_code_93700: Double
                   )
  //解析Credit文件每行数据,将值存入Credit中
  def parseCredit(consumerRecord: Array[Double]): Credit = {
    Credit(
      consumerRecord(1), consumerRecord(2), consumerRecord(3), consumerRecord(4), consumerRecord(5),
      consumerRecord(6), consumerRecord(7), consumerRecord(8), consumerRecord(9), consumerRecord(10),
      consumerRecord(11), consumerRecord(12), consumerRecord(13), consumerRecord(14), consumerRecord(15),
      consumerRecord(16), consumerRecord(17), consumerRecord(18), consumerRecord(19), consumerRecord(20),
      consumerRecord(21), consumerRecord(22), consumerRecord(23), consumerRecord(24), consumerRecord(25),
      consumerRecord(26), consumerRecord(27), consumerRecord(28), consumerRecord(29), consumerRecord(30),
      consumerRecord(31), consumerRecord(32), consumerRecord(33), consumerRecord(34), consumerRecord(35),
      consumerRecord(36), consumerRecord(37), consumerRecord(38), consumerRecord(39), consumerRecord(40),
      consumerRecord(41), consumerRecord(42), consumerRecord(43), consumerRecord(44), consumerRecord(45),
      consumerRecord(46), consumerRecord(47), consumerRecord(48), consumerRecord(49), consumerRecord(50),
      consumerRecord(51), consumerRecord(52), consumerRecord(53), consumerRecord(54), consumerRecord(55),
      consumerRecord(56), consumerRecord(57), consumerRecord(58), consumerRecord(59), consumerRecord(60),
      consumerRecord(61), consumerRecord(62), consumerRecord(63), consumerRecord(64), consumerRecord(65),
      consumerRecord(66), consumerRecord(67), consumerRecord(68), consumerRecord(69), consumerRecord(70),
      consumerRecord(71), consumerRecord(72), consumerRecord(73), consumerRecord(74), consumerRecord(75),
      consumerRecord(76), consumerRecord(77), consumerRecord(78), consumerRecord(79), consumerRecord(80),
      consumerRecord(81)
    )
  }

  //将数据类型从String转换成Double类型
  def parseRDD(rdd: RDD[String]): RDD[Array[Double]] = {
    rdd.map(_.split(",")).map(_.map(_.toDouble))
  }

  def main(args: Array[String]): Unit = {

    //1. 准备实时环境
    val sparkConf: SparkConf = new SparkConf().setAppName("ods_base_data_app").setMaster("local[4]")
    val ssc: StreamingContext = new StreamingContext(sparkConf , Seconds(5))
    val spark = SparkSession.builder.master("local[4]").getOrCreate
    val sc = spark.sparkContext


    val hadoopConf = sc.hadoopConfiguration
    hadoopConf.set("fs.defaultFS", "hdfs://node1:9000/")
    val fileSystem = FileSystem.get(hadoopConf)

    //2. 从kafka中消费数据
    val topicName : String = "CREDIT_DATA"  //对应生成器配置中的主题名
    val groupId : String = "CREDIT_DATA"
    val kafkaDStream :InputDStream[ConsumerRecord[String, String]] = MyKafkaUtils.getKafkaDStream(ssc, topicName, groupId)
    if (kafkaDStream != null) {
      val creditDStream: DStream[Array[Double]] = kafkaDStream.map(
        consumerRecord => {
          val line = consumerRecord.value().split(',').map(_.toDouble)
          line
        }
      )

      creditDStream.foreachRDD(
        creditRDD => {
          // 结构转换
          val credit = creditRDD.map(parseCredit)

          val creditDF = spark.createDataFrame(credit).cache()
          if (!creditDF.isEmpty) {
            // 原始数据入库
            println("开始写入数据库……")
            MySQLUtils.dfSaveToMySQL(creditDF)
            println("初始数据写入完成，成功写入：" + creditDF.count() + "行")
          }

          creditDF.registerTempTable("credit")
          // creditDF.show()

          // 随机森林训练模型
          if (!creditDF.isEmpty){
            val featureCols  = Array("loan_amnt", "term", "int_rate", "installment", "annual_inc",
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
              "zip_code_93700")

            val assembler = new VectorAssembler().setInputCols(featureCols).setOutputCol("features")
            val newCreditDF1 = assembler.transform(creditDF)
            val labelIndexer = new StringIndexer().setInputCol("loan_status").setOutputCol("label")
            val newCreditDF2 = labelIndexer.fit(newCreditDF1).transform(newCreditDF1)


            // 划分模型数据
            val splitSeed = 5043
            val Array(trainingData, testData) = newCreditDF2.randomSplit(Array(0.75, 0.25), splitSeed)

            trainingData.show()
            println()
            testData.show()
            val classifier = new RandomForestClassifier()
              .setImpurity("gini")
              .setMaxDepth(5)
              .setNumTrees(50)
              .setFeatureSubsetStrategy("auto")
              .setSeed(5043)

            // 生成唯一模型id名称
            val modelName = UUID.randomUUID()

            val strPath = "/creditRiskControl/models/credit-risk-control-object"
            val modelType = "random-forest-classifier"
            val path = new Path(strPath + "-" + modelType + "-" + modelName)
            val oos = new ObjectOutputStream(new FSDataOutputStream(fileSystem.create(path)))

            val randomForestClassifierModel = classifier.fit(trainingData)

            // 存储模型到hdfs
            if (randomForestClassifierModel != null) {
              oos.writeObject(randomForestClassifierModel)
            }
            oos.close()

            val evaluator = new BinaryClassificationEvaluator().setLabelCol("label")

            // 测试训练模型
            val predictions = randomForestClassifierModel.transform(testData)

            // 模型结果评估
            randomForestClassifierModel.toDebugString
            val accuracy = evaluator.evaluate(predictions)
            println("accuracy before pipeline fitting: " + accuracy)

            val rm = new RegressionMetrics(
              predictions.select("prediction", "label").rdd.map(x =>
                (x(0).asInstanceOf[Double], x(1).asInstanceOf[Double]))
            )
            println("MSE: " + rm.meanSquaredError)
            println("MAE: " + rm.meanAbsoluteError)
            println("RMSE Squared: " + rm.rootMeanSquaredError)
            println("R Squared: " + rm.r2)
            println()

            // 写入mysql
            MySQLUtils.saveToMySQL(new ModelInfo(
              modelName.toString, strPath, accuracy,
              rm.meanSquaredError, rm.meanAbsoluteError, rm.rootMeanSquaredError, modelType))

            // pilelien模型调参
            val paramGrid = new ParamGridBuilder()
              .addGrid(classifier.maxBins, Array(25, 28, 31))
              .addGrid(classifier.maxDepth, Array(4, 6, 8))
              .addGrid(classifier.impurity, Array("entropy", "gini"))
              .build()

            val steps: Array[PipelineStage] = Array(classifier)
            val pipeline = new Pipeline().setStages(steps)

            val evaluator2 = new BinaryClassificationEvaluator().setLabelCol("label")
            val cv = new CrossValidator()
              .setEstimator(pipeline)
              .setEvaluator(evaluator2)
              .setEstimatorParamMaps(paramGrid)
              .setNumFolds(10)
            val pipelineFittedModel = cv.fit(trainingData)

            // 生成唯一模型名称
            val pipelineModelName = UUID.randomUUID()
            println(pipelineModelName)

            // 写入hdfs
            val modelType2 = "pipeline-random-forest-classifier"
            val path2 = new Path(strPath + "-" + modelType2 + "-" + pipelineModelName)
            val oos2 = new ObjectOutputStream(new FSDataOutputStream(fileSystem.create(path2)))
            if (pipelineFittedModel != null) {
              oos2.writeObject(pipelineFittedModel)
            }
            oos2.close()

            /*//这里示例另外一个程序直接从hdfs读取序列化对象使用模型
            val ois = new ObjectInputStream(new FSDataInputStream(fileSystem.open(path)))
            val sample_model = ois.readObject.asInstanceOf[Word2VecModel]*/

            val predictions2 = pipelineFittedModel.transform(testData)

            val accuracy2 = evaluator2.evaluate(predictions2)
            println("accuracy after pipeline fitting: " + accuracy2)

            println(pipelineFittedModel.bestModel.asInstanceOf[org.apache.spark.ml.PipelineModel].stages(0))

            pipelineFittedModel
              .bestModel.asInstanceOf[org.apache.spark.ml.PipelineModel]
              .stages(0)
              .extractParamMap

            val rm2 = new RegressionMetrics(
              predictions2.select("prediction", "label").rdd.map(x =>
                (x(0).asInstanceOf[Double], x(1).asInstanceOf[Double]))
            )
            println("MSE: " + rm2.meanSquaredError)
            println("MAE: " + rm2.meanAbsoluteError)
            println("RMSE Squared: " + rm2.rootMeanSquaredError)
            println("R Squared: " + rm2.r2)

            // 写入mysql
            MySQLUtils.saveToMySQL(new ModelInfo(
              pipelineModelName.toString, strPath, accuracy2,
              rm2.meanSquaredError, rm2.meanAbsoluteError, rm2.rootMeanSquaredError, modelType2))

          }
          /*// 决策树训练模型
          val numClasses = 3
          val categoricalFeaturesInfo = java.util.Map[Integer, Integer]
          val impurity = "gini"
          val maxDepth = 5
          val maxBins = 32
          val model = DecisionTree.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo, impurity, maxDepth, maxBins)*/

          /* // 神经网络训训练模型
           val NNmodel = new NeuralNet().
             setSize(Array(5, 7, 1)).
             setLayer(3).
             setActivation_function("tanh_opt").
             setLearningRate(2.0).
             setScaling_learningRate(1.0).
             setWeightPenaltyL2(0.0).
             setNonSparsityPenalty(0.0).
             setSparsityTarget(0.05).
             setInputZeroMaskedFraction(0.0).
             setDropoutFraction(0.0).
             setOutput_function("sigm").
             NNtrain(trainingData, opts)*/
        }
      )
    }

    ssc.start()
    ssc.awaitTermination()
  }
}
