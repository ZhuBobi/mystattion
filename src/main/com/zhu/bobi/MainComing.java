package com.zhu.bobi;

import com.zhu.bobi.entity.BaseId;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Created by Zhu波比 on 2018/3/31.
 */
@SpringBootApplication//(scanBasePackages = {"com.zhu.bobi.dao","com.zhu.bobi.service"})
public class MainComing {
  public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
    SpringApplication.run(MainComing.class, args);
  }

  public static void myBatisEntityBulider() {
    try {
      List<String> warnings = new ArrayList<>();
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      InputStream is = classloader.getResourceAsStream("config/mybatis/generator.xml");
      ConfigurationParser cp = new ConfigurationParser(warnings);
      Configuration config = cp.parseConfiguration(is);
      DefaultShellCallback callback = new DefaultShellCallback(true);
      MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
      myBatisGenerator.generate(null);
    } catch (IOException | InterruptedException | InvalidConfigurationException | XMLParserException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static void test(Class<?extends BaseId> clazz) throws  ClassNotFoundException, IllegalAccessException, InstantiationException {
     Object obj = Class.forName(clazz.getName()).newInstance();
  }



  public static void  testt(){
    int[] a={0,1,2,3,4};
    for (int i : a) {
        System.out.println(i);
    }
  }
}