package com.interview.javabasics.io.file;



import javax.swing.text.AbstractDocument;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 19:22
 * @Description: Java 8 Files 练习
 */
public class FilesDemo {
  public static void main(String[] args) throws IOException {
    String path = "C:\\Users\\Administrator\\Desktop\\新建文本文档.txt";
    String conent = "新加入234";
    byte[] data = Files.readAllBytes(Paths.get(path));
    System.out.println(new String(data, StandardCharsets.UTF_8));
    Files.write(Paths.get(path), conent.getBytes(),  StandardOpenOption.APPEND);

  }
}
