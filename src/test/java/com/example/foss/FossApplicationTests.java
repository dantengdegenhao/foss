package com.example.foss;

import com.example.foss.mapper.OrderDao;
import com.example.foss.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FossApplicationTests {
	@Autowired
	OrderDao orderDao;
	@Test
	public void contextLoads() {
		System.out.println(orderDao.queryOrder(new Order()));
	}

}
