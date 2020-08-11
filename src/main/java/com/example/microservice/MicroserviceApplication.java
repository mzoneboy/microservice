package com.example.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MicroserviceApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/stulist")
	public List<Map<String, Object>> hello() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT id,name FROM test_db.student ", new Object[]{});
		return list;
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

}
