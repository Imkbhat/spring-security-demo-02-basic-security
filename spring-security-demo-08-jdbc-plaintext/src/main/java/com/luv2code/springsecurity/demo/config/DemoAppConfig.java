package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-postgresql.properties")
public class DemoAppConfig {

	// set up a variable to hold ppts
	@Autowired
	private Environment env;

	//logger
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	//Bean for securiity data src
	@Bean
	public DataSource securityDataSource () {
		
		ComboPooledDataSource secDataSrc = new ComboPooledDataSource();
		
		try {
			secDataSrc.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		logger.info(">>>>>>>>>>>>JDBC URL" +env.getProperty("jdbc.url"));
		
		logger.info(">>>>>>>>>>>>JDBC USER" +env.getProperty("jdbc.user"));
		
		secDataSrc.setJdbcUrl(env.getProperty("jdbc.url"));
		
		secDataSrc.setUser(env.getProperty("jdbc.user"));
		
		secDataSrc.setPassword(env.getProperty("jdbc.password"));
		
		secDataSrc.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		
		secDataSrc.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		
		secDataSrc.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		
		secDataSrc.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return secDataSrc;
	}
	
	//helper to read ppt
	
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
	
}