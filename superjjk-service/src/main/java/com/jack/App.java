package com.jack;

import com.jack.config.DefaultConfig;
import com.zhuxingsheng.remote.RmoteInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

//兼备了@EnableAutoConfiguration和@ComponentScan 注解的功能
@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({DefaultConfig.class})
@ComponentScan(value = {"com.zhuxingsheng","com.jack"} )
public class App {

//    @Autowired
//    ServiceClientProxyCreator creator;


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}



