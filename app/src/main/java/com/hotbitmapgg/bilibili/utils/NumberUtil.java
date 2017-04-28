package com.hotbitmapgg.bilibili.utils;

/**
 * Designed by guoyx on 2016/10/11 14:35

 */

public class NumberUtil {

  public static String converString(int num) {

    if (num < 100000) {
      return String.valueOf(num);
    }
    String unit = "万";
    double newNum = num / 10000.0;

    String numStr = String.format("%." + 1 + "forwarded", newNum);
    return numStr + unit;
  }
}
