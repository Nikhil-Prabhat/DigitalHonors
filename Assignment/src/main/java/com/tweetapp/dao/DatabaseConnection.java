package com.tweetapp.dao;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatabaseConnection {

	private static String DB_DRIVER_CLASS_NAME = "driver.class.name";
	private static String DB_URL = "db.url";
	private static String DB_USERNAME = "db.username";
	private static String DB_PASSWORD = "db.password";

	public static JdbcTemplate getJdbcTemplate() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("db.properties"));

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS_NAME));
		driverManagerDataSource.setUrl(properties.getProperty(DB_URL));
		driverManagerDataSource.setUsername(properties.getProperty(DB_USERNAME));
		driverManagerDataSource.setPassword(properties.getProperty(DB_PASSWORD));

		JdbcTemplate jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
		return jdbcTemplate;

	}

}
