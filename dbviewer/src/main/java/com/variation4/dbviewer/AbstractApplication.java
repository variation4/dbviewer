package com.variation4.dbviewer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractApplication {

	private Dao init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		return (Dao) context.getBean("dao");
	}

	public abstract void doScript(Dao dao);

	public void execute() {
		Dao dao = init();
		doScript(dao);
	}

}
