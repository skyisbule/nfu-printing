package com.github.skyisbule.print;

import cn.hutool.setting.dialect.Props;
import com.github.skyisbule.print.exception.ExceptionMessage;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableSwagger2Doc
@EnableTransactionManagement
@MapperScan("com.github.skyisbule.print.*")
@SpringBootApplication
public class WarehouseApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {

    Props props = new Props("errorMessage.properties");
    props.forEach((key, value) -> {
        ExceptionMessage.message.put(key.toString(),value.toString());
    });

    SpringApplication.run(WarehouseApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(WarehouseApplication.class);
  }

}
