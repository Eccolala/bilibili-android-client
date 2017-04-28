package com.hotbitmapgg.bilibili.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Designed by guoyx on 16/8/20 12:18

 * <p/>
 * 一个简单的SnackBar工具类
 */
public class SnackbarUtil {

  public static void showMessage(View view, String text) {

    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
  }
}
