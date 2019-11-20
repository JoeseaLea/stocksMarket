package com.joesea.stocksmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(value = "com.joesea.stocksmarket.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StocksmarketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StocksmarketApplication.class, args);
	}

	@Override
	public void run(String... args) {

	}

}
