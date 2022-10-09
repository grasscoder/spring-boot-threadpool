package org.test;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.test.runnable.OutputTask;

@MapperScan("org.test.mapper")
@SpringBootApplication
public class ApplicationServer {
    private static final Logger logger= LoggerFactory.getLogger(ApplicationServer.class);


    public static void main(String[] args) {
        logger.info("服务开始启动");
        SpringApplication.run(ApplicationServer.class);
        logger.info("服务启动完成");
    }
}
