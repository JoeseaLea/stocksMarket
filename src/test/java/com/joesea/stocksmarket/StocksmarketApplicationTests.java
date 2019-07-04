package com.joesea.stocksmarket;

import java.util.UUID;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class StocksmarketApplicationTests {

//	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
	}

}
