package com.stocksmarket;

import com.stocksmarket.init.ApplicationInit;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(value = "com.stocksmarket.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StocksmarketApplication implements CommandLineRunner {

	@Autowired
	private ApplicationInit applicationInit;

	public static void main(String[] args) {
		SpringApplication.run(StocksmarketApplication.class, args);
	}

	@Override
	public void run(String... args) {
		applicationInit.init();
	}

}
