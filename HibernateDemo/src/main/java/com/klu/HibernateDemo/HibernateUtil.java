 package com.klu.HibernateDemo;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
		
		private static SessionFactory sessionFactory;
		 public static SessionFactory getSessionFactory() {
		  if(sessionFactory == null) {
		   Configuration configuration = new Configuration();
		   Properties props = new Properties();
		   props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		   props.put(Environment.URL, "jdbc:mysql://localhost:3306/jfsddemo");
		   props.put(Environment.USER, "root");
		   props.put(Environment.PASS, "root");
		   props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
		   props.put(Environment.HBM2DDL_AUTO, "update");
		   props.put(Environment.SHOW_SQL, true);
		   props.put(Environment.FORMAT_SQL, true);
		   
		   configuration.addProperties(props);
		   configuration.addAnnotatedClass(Student.class);

		   
		   ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		   
		   sessionFactory = configuration.buildSessionFactory(registry);
		  }
		  return sessionFactory;
		 }


	}
