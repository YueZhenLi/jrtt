package com.cskaoyan.smzdm;

import com.cskaoyan.smzdm.dao.UserMapper;
import com.cskaoyan.smzdm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SmzdmApplication.class)
@WebAppConfiguration
public class SmzdmApplicationTests {

	@Autowired
	UserMapper userMapper;

	@Test
	public void contextLoads() {
		System.out.println("all right!");
	}

	@Test
	public void UserMapper(){
		User userById = userMapper.findUserById(112);
		System.out.println("userById="+userById);
	}

}
