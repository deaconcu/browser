package com.jike.mobile.browser.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class ItemTest {

	@Test
	public void testConstruct() {
		
		
	}
	
	@Test
	public void testUpdate() {
		System.out.println("------------testUpdate---------------");
		Configuration cfg = new Configuration()
			.addClass(com.jike.mobile.browser.model.Item.class).addClass(com.jike.mobile.browser.model.Category.class)
			.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
			.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/browser")
			.setProperty("hibernate.connection.password", "zxcvbnm")
			.setProperty("hibernate.connection.username", "root");
		
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		

		Category category = (Category) session.get(Category.class, 1);
		System.out.println(category.getName());
		
		
		

	}
}
