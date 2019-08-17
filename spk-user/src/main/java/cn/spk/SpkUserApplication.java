package cn.spk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("cn.spk.dao")
@EnableEurekaClient
@SpringBootApplication
public class SpkUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpkUserApplication.class, args);
    }

}
