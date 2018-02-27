package com.baobaotao.base.multipledatasource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MultipleSessionFactory {
	private final static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static SessionFactory getSessionFactory(String sessionFactoryBeanId) {
		return (SessionFactory) applicationContext.getBean(sessionFactoryBeanId);
	}

	public static Session getSession(String sessionFactoryBeanId) {
		return getSessionFactory(sessionFactoryBeanId).openSession();
	}
}
