package com.common.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.common.system.entity.ActEntity;
import com.common.system.service.ActService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonAdminApplicationTests {

    @Autowired
    private ActService actService;
	
	@Test
	public void contextLoads() {
		ActEntity rule = actService.getById(1);
		System.out.println("aaa"+rule);
	}
}
