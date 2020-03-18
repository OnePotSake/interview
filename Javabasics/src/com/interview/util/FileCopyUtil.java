package com.interview.util;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 18:14
 * @Description: 文件复制, 文件夹复制
 */
public class FileCopyUtil {

  /***
   * 功能描述:
   * //复制文件
   * @param oldfilepath(源文件路径),newfilepath(目标文件夹路径),flage(是否覆盖同名文件)
   * @return: void 
   * @since: 1.0.0
   * @Author:Darks
   * @Date: 2020/3/18 18:31
   */
  public static void copyFile(String oldfilepath, String newpath, boolean flage) {//复制文件
    File oldfile = new File(oldfilepath);
    File newfile = new File(newpath + File.separator + oldfile.getName());//创建新抽象文件
    if (!oldfile.exists() || !oldfile.isFile()) {
      System.out.println("源文件不存在");
      return;
    }
    long startTime = System.currentTimeMillis();
    System.out.println("start copy file,now time" + new Date());
    if (newfile.exists()) {//新文件路径下有同名文件
      System.out.println("是否覆盖原文件" + flage);
      if (flage) {
        newfile.delete();
        try {
          newfile.createNewFile();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {
        newfile = new File(newpath + File.separator + "(1)" + newfile.getName());
        try {
          newfile.createNewFile();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    } else {
      try {
        newfile.createNewFile();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    try {
      FileInputStream fin = new FileInputStream(oldfile);//输入流
      try {
        FileOutputStream fout = new FileOutputStream(newfile, true);//输出流
        byte[] b = new byte[1024];
        try {
          while ((fin.read(b)) != -1) {//读取到末尾 返回-1 否则返回读取的字节个数
            fout.write(b);
          }
          fin.close();
          fout.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("end time " + (Calendar.getInstance().getTimeInMillis() - startTime));
  }

  /***
   * 功能描述:
   * // 复制文件
   * @param oldfile(源文件),newfile(目标文件夹)
   * @return: void
   * @since: 1.0.0
   * @Author:Darks
   * @Date: 2020/3/18 18:37
   */
  public static void copyFile(File oldfile, File newfile) {
    long startTime = System.currentTimeMillis();
    System.out.println("start copy file,now time" + new Date());
    if (oldfile.isFile() && oldfile.exists()) {//文件
      copyFile(oldfile.getAbsolutePath(), newfile.getAbsolutePath(), false);
      return;
    }
    if (oldfile.isDirectory()) {
      File file2 = new File(newfile.getAbsoluteFile() + File.separator + oldfile.getName());
      file2.mkdirs();
      String[] list = oldfile.list();
      for (int i = 0; i < list.length; i++) {
        File file1 = new File(oldfile.getAbsoluteFile() + File.separator + list[i]);
        copyFile(file1, file2);
      }
    }
    System.out.println("end time " + (Calendar.getInstance().getTimeInMillis() - startTime));
  }

  /***
   * 功能描述:
   * //
   * @param oldfilepath(源文件夹路径),newfilepath(目标文件夹路径),flage(是否覆盖同名文件)
   * @return: void
   * @since: 1.0.0
   * @Author:Darks
   * @Date: 2020/3/18 18:29
   */
  public static void copydir(String oldfilepath, String newfilepath, boolean flage) {//复制文件夹
    File oldfile = new File(oldfilepath);
    File newfile = new File(newfilepath + File.separator + oldfile.getName());
    if (!oldfile.exists() || !oldfile.isDirectory()) {
      System.out.println("此文件夹不存在！");
      return;
    }

    if (newfile.exists()) {
      System.out.println("是否覆盖原有文件夹{}" + flage);
      if (flage) {
        deleteFile(newfile.getAbsolutePath());

      } else
        return;
    }
    copyFile(oldfile, new File(newfilepath));

    return;

  }

  /***
   * 功能描述:
   * //删除文件或者文件夹下内容
   * @param path 文件或文件夹全路径地址
   * @return: void 
   * @since: 1.0.0
   * @Author:Darks
   * @Date: 2020/3/18 18:28
   */
  public static void deleteFile(String path) {
    long startTime = System.currentTimeMillis();
    System.out.println("start delete file,now time" + new Date());
    File file = new File(path);
    if (file.isFile()) {
      file.delete();
    } else if (file.listFiles().length > 0) {
      for (File f : file.listFiles()) {
        file.delete();
      }
    }
    System.out.println("end delete file,now time" + new Date());
    System.out.println("spend time" + (Calendar.getInstance().getTimeInMillis() - startTime));
  }


}
