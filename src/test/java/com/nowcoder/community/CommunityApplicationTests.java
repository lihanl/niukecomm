package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;
//	@Test
//	void contextLoads() {
//	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
//		AlphaDao alphadao = applicationContext.getBean(AlphaDao.class);
//		System.out.println(alphadao.select());

		AlphaDao alphadao = (AlphaDao) applicationContext.getBean("alphadaohibernate");
		System.out.println(alphadao.select());

	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaservice = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaservice);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));

	}

	@Autowired
	@Qualifier("alphadaohibernate")
	private AlphaDao alphadao;

	@Test
	public void testDI(){
		System.out.println(alphadao);
	}

}
