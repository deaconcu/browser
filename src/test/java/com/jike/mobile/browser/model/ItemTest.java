package com.jike.mobile.browser.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class ItemTest {

	@Test
	public void testConstruct() {
		Item item = new Item();
		System.out.println(item.getName());
		System.out.println(item.getCategory());
		System.out.println(item.getId());
		System.out.println(item.getSizeInByte());
		
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
		
		Transaction tx = session.beginTransaction();

		Category category = (Category) session.get(Category.class, 1);
		System.out.println(category.getName());
		
		
		Item item = new Item(65, "9", "1", "1", 1, "1", "1", "1", "1", "1", category);
		//Object o = session.save(item);
		
		//System.out.println(o);
		
		//Item item2 = (Item) session.get(Item.class, 45);
		
		//session.update(item);
		
		System.out.println("1: " + item.getId());

		tx.commit();
		System.out.println("2: " + item.getId());
		session.flush();
		System.out.println("3: " + item.getId());
		session.close();
		System.out.println("4: " + item.getId());
		
		System.out.println("------------testConstruct---------------");
		
		Item item2 = new Item("", "", "", 0, "", "", "", "", "", null);
		
		System.out.println("itemid: " + item2.getId());

	}
}
