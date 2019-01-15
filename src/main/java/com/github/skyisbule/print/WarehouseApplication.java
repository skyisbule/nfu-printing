package com.github.skyisbule.print;

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
    SpringApplication.run(WarehouseApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(WarehouseApplication.class);
  }

}
