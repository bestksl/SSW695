package com.hobbymatcher.config.dao;

import java.beans.PropertyVetoException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@MapperScan("com.hobbymatcher.dao")
public class DataSourceConfiguration {

	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Bean(name = "dataSource")
	public ComboPooledDataSource createComboPooledDataSource() throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();

		ds.setDriverClass(jdbcDriver);
		ds.setJdbcUrl(jdbcUrl);
		ds.setUser(jdbcUsername);
		ds.setPassword(jdbcPassword);
		ds.setMaxPoolSize(30);
		ds.setMinPoolSize(10);
		ds.setAutoCommitOnClose(false);
		ds.setCheckoutTimeout(10000);
		ds.setAcquireRetryAttempts(2);

		return ds;
	}
}
