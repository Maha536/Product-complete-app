package com.cts.training.mavenweb.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.cts.training.mavenweb")
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class ServletConfig {

	
	@Autowired
	private Environment env;
	
	// Config the DataSource ( ComboPooled DataSource)
	// not exposing as bean, will use here only to configure SessionFactory
	private DataSource comboPooledDataSource() {
		// highly configurable as compared to DriverManagerDataSource
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		// JDBC Config
		
		try {
			comboPooledDataSource.setDriverClass(this.env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboPooledDataSource.setJdbcUrl(this.env.getProperty("jdbc.url"));
		comboPooledDataSource.setUser(this.env.getProperty("jdbc.user"));
		comboPooledDataSource.setPassword(this.env.getProperty("jdbc.password"));
		
		// Combo Pooled Config
		comboPooledDataSource.setInitialPoolSize(Integer.parseInt(this.env.getProperty("combopool.initialPoolSize")));
		comboPooledDataSource.setMaxPoolSize(Integer.parseInt(this.env.getProperty("combopool.maxPoolSize")));
		comboPooledDataSource.setMinPoolSize(Integer.parseInt(this.env.getProperty("combopool.minPoolSize")));
		comboPooledDataSource.setMaxIdleTime(Integer.parseInt(this.env.getProperty("combopool.maxIdleTime")));
		
		return comboPooledDataSource;
		
	}
	
	// config the hibernate properties
	// collection of MAP type ( Properties)
	private Properties hibernateProperties() {
		
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", this.env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql",this.env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", this.env.getProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		// Initial SessionFactory Object
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// 1. Set up the DB Connection (Data Source)
		sessionFactory.setDataSource(this.comboPooledDataSource());
		
		// 2. Set up the hibernate Properties
		sessionFactory.setHibernateProperties(this.hibernateProperties());
		
		// 3. set up the Entity Scanning Path
		sessionFactory.setPackagesToScan(this.env.getProperty("hibernate.packagesToScan"));
		
		return sessionFactory;
	}
	
	// method to configure the HibernateTransaction
	@Autowired
	@Bean // exposing for Spring to be used internally
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		
		// need to setup transaction on SessionFactory
		// all transaction for SessionFactory  will be managed by hibernateTransactionManager behind the scene 
		txManager.setSessionFactory(sessionFactory);
		
		return txManager;
	}
	
	
	// add configuration for DataSource for JDBCTemplate
	// configure the data source and return a bean of DataSource
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	
		// config the object
		/*dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/studentdb");
		dataSource.setUsername("root");
		dataSource.setPassword("");*/
		
		/*dataSource.setDriverClassName(this.jdbcDriver);
		dataSource.setUrl(this.jdbcUrl);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);*/
		
		dataSource.setDriverClassName(this.env.getProperty("jdbc.driver"));
		dataSource.setUrl(this.env.getProperty("jdbc.url"));
		dataSource.setUsername(this.env.getProperty("jdbc.user"));
		dataSource.setPassword(this.env.getProperty("jdbc.password"));
		
		// return the bean
		return dataSource;
	}
	
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// inject dependency
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
}
