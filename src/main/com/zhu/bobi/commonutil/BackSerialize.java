package com.zhu.bobi.commonutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class BackSerialize {

  private final static Logger logger = LoggerFactory.getLogger(BackSerialize.class);

  private static boolean getController = false;

  public static void main(String[] args) {
    try {
      String path = new File(ResourceUtils.getURL("classpath:").getPath()).getPath();
      System.out.println(path);


    } catch (FileNotFoundException e) {
      logger.error("文件不存在！");
    }
  }
}
