package com.interview.util;

import java.text.DecimalFormat;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 17:34
 * @Description: 文件大小换算工具
 */
public class FileSizeUtil {
  /***
   * 功能描述:
   * //换算内容大小
   * @param size
   * @return: java.lang.String 
   * @since: 1.0.0
   * @Author:Darks
   * @Date: 2020/3/18 17:35
   */
  public static String getPrintSize(long size) {
    // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
    if (size < 1024) {
      return String.valueOf(size) + "B";
    } else {
      size = size / 1024;
    }
    // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
    // 因为还没有到达要使用另一个单位的时候
    // 接下去以此类推
    if (size < 1024) {
      return String.valueOf(size) + "KB";
    } else {
      size = size / 1024;
    }
    if (size < 1024) {
      // 因为如果以MB为单位的话，要保留最后1位小数，
      // 因此，把此数乘以100之后再取余
      size = size * 100;
      return String.valueOf((size / 100)) + "."
          + String.valueOf((size % 100)) + "MB";
    } else {
      // 否则如果要以GB为单位的，先除于1024再作同样的处理
      size = size * 100 / 1024;
      return String.valueOf((size / 100)) + "."
          + String.valueOf((size % 100)) + "GB";
    }
  }
  /***
   * 功能描述:
   * //换算内容大小,高效方式,但会相对进位
   * @param size
   * @return: java.lang.String
   * @since: 1.0.0
   * @Author:Darks
   * @Date: 2020/3/18 17:35
   */
    public static String readableFileSize(long size) {
      if (size <= 0) return "0";
      final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
      int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
      return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
