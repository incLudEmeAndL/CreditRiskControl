package com.chenwei.csust.model

case class ModelInfo(var name:String, var path:String, var accuracy:Double,
                     var mse:Double, var mae:Double, var rmseSquared:Double, var modelType:String) {
/*  private var name:String=_
  private var path:String=_
  private var accuracy:Double=_
  private var mse:Double=_
  private var mae:Double=_
  private var rmseSquared:Double=_*/
}
