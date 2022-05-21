package com.chenwei.csust.util

import java.sql.{Connection, DriverManager}

import com.chenwei.csust.model.ModelInfo
import org.apache.spark.sql.{DataFrame, SaveMode}

object MySQLUtils {
  def getMysqlConn():Connection = {
    Class.forName(MyPropsUtils(MySQLConfig.DRIVER))
    DriverManager.getConnection(MyPropsUtils(MySQLConfig.URL),
      MyPropsUtils(MySQLConfig.USER_NAME),MyPropsUtils(MySQLConfig.PASSWORD))
  }

  def dfSaveToMySQL(dataFrame: DataFrame): Unit ={
    dataFrame.write
      .format("jdbc")
      .option("url", MyPropsUtils(MySQLConfig.URL))
      .option("dbtable", "original_data") // 原始数据表
      .option("user", MyPropsUtils(MySQLConfig.USER_NAME))
      .option("password", MyPropsUtils(MySQLConfig.PASSWORD))
      .mode(SaveMode.Append)
      .save()
  }

  def saveToMySQL(modelInfo: ModelInfo):Unit = {
    val conn = getMysqlConn()
    val sql = "insert into model_info(name,path,accuracy,mse,mae,rmseSquared,type,createdAt) values(?,?,?,?,?,?,?,?)"

    val statement = conn.prepareStatement(sql)
    statement.setString(1, modelInfo.name)
    statement.setString(2, modelInfo.path)
    statement.setDouble(3, modelInfo.accuracy)
    statement.setDouble(4, modelInfo.mse)
    statement.setDouble(5, modelInfo.mae)
    statement.setDouble(6, modelInfo.rmseSquared)
    statement.setString(7, modelInfo.modelType)
    statement.setString(8, DateUtil.nowDate())

    statement.executeUpdate()

    statement.close()
    conn.close()
  }
}
