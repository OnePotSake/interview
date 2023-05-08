package com.interview.javabasics.io.file;

import com.interview.util.FileCopyUtil;
import com.interview.util.FileSizeUtil;

import java.io.*;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 17:01
 * @Description: 文件相关
 */
public class FileDemo {
  public static void main(String[] args) {
    String path = "D:/tool/xx/Sx.exe";
    File file = new File("ShadowsocksR-dotnet4.0.exe", "D:/tool/ShadowsocksR-win-4.9.2");
    String otherPath = "..\\README.md";
    File otherFile = new File(otherPath);
    try {
      getFileInfo(file, otherFile);
      readFile(otherPath);
      writeFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void writeFile() throws IOException {
    PrintWriter printWriter = new PrintWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\sg.txt", true), true);
    for (int i = 0; i < 3; i++) {
      printWriter.println(i);
    }
  }

  private static void readFile(String otherPath) throws IOException {
    InputStreamReader reader = new InputStreamReader(new FileInputStream(otherPath), "UTF-8");
    BufferedReader bfreader = new BufferedReader(reader);
    String line;
    while ((line = bfreader.readLine()) != null) {
      System.out.println(line);
    }
  }

  private static void getFileInfo(File file, File otherFile) throws IOException {
    System.out.println("文件路径相对:" + file.getCanonicalPath());
    System.out.println("文件路径绝对:" + file.getAbsolutePath());
    System.out.println("------------------------------------");
    System.out.println("文件路径:" + otherFile.getCanonicalPath());
    System.out.println("文件路径:" + otherFile.getAbsolutePath());
    System.out.println("------------------------------------");
    // 分区后的剩余磁盘容量
    System.out.println(FileSizeUtil.getPrintSize(otherFile.getFreeSpace()));
    // 分区后的磁盘剩余容量 还包含了另外一些功能来检查写许可和其它操作系统限制，这将返回一个可用空间数的更好的估计值 待验证
    System.out.println(FileSizeUtil.getPrintSize(otherFile.getUsableSpace()));
    // 分区后的磁盘总容量
    System.out.println(FileSizeUtil.getPrintSize(otherFile.getTotalSpace()));
  }
}
