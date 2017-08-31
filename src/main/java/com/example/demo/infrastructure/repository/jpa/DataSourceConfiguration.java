package com.example.demo.infrastructure.repository.jpa;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"default"})
public class DataSourceConfiguration 
{
	@Bean
	@ConfigurationProperties(prefix = "datasource.mysql")
	public DataSource dataSource()
	{
		return DataSourceBuilder.create().build();
	}
}
