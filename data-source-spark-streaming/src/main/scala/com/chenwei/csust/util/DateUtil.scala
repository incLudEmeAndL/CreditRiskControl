package com.chenwei.csust.util

import java.text.SimpleDateFormat
import java.util.Date

object DateUtil {
  def nowDate(): String = {
    val now : Date = new Date()
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    dateFormat.format(now)
  }
}
