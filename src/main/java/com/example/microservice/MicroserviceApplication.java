package com.example.microservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
//import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

//@EnableDubboConfiguration
@MapperScan("com.example.microservice.mapper")
@EnableDubbo
@EnableJms    // 启动消息队列
@RestController
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class MicroserviceApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World11!";
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

}
