package com.activity.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.activity.util.HibernateUtil;

@WebListener //監聽器註冊方法，二擇一，一用@註解，二是在web.xml設定(小吳老師有做範本)
public class InitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {//開始時要做什麼
		System.out.println("context started");
		HibernateUtil.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {//結束要做什麼
		System.out.println("context ended");
		HibernateUtil.shutdown();
	}

}
