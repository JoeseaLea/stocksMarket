package com.joesea.stocksmarket;

import com.joesea.stocksmarket.dao.StockDao;
import com.joesea.stocksmarket.service.StockCodeAndNameDownService;
import com.joesea.stocksmarket.uitl.StockCurDataDownUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(value = "com.joesea.stocksmarket.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StocksmarketApplication implements CommandLineRunner {

	@Autowired
	private StockCodeAndNameDownService stockCodeAndNameDownService;
	@Autowired
	private StockDao stockDao;

	@Value("${stock.curdata.down.host}")
	private String host;

	public static void main(String[] args) {
		SpringApplication.run(StocksmarketApplication.class, args);
	}

	@Override
	public void run(String... args) {
		init();
//		stockCodeAndNameDownService.downAllStockCodeAndName();
//		List<String> stockCodes = stockDao.getAllStockCode();
//		System.out.println(stockCodes);
	}

	private void init() {
		StockCurDataDownUtil.setHost(host);
		stockCodeAndNameDownService.downAllStockCodeAndName();
	}
}
