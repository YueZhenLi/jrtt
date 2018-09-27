package com.cskaoyan.smzdm;

import com.cskaoyan.smzdm.dao.UserMapper;
import com.cskaoyan.smzdm.domain.News;
import com.cskaoyan.smzdm.service.NewsService;
import com.cskaoyan.smzdm.service.impl.NewsServiceImpl;
import com.cskaoyan.smzdm.util.RedisUtil;
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

	@Autowired
	NewsService newsService;

	@Test
	public void contextLoads() {
		System.out.println("all right!");
	}

	@Test
	public void UserMapper(){
		Long sadd = RedisUtil.sadd("test", "test1", "test2");
		System.out.println(sadd);
	}

}
