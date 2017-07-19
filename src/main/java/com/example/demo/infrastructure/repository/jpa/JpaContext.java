package com.example.demo.infrastructure.repository.jpa;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.example.demo.infrastructure.repository.jpa")
@EnableTransactionManagement
public class JpaContext 
{
	@Autowired
	@Bean
	public JpaTransactionManager transactionManager(AbstractEntityManagerFactoryBean entityManagerFactory)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}
	
	@Autowired
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("com.example.demo.infrastructure.repository.jpa");
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProperties.put("hibernate.show_sql", true);
		entityManagerFactory.setJpaProperties(jpaProperties);
		
		return entityManagerFactory;
	}
	
	@Bean
	public DataSource emmbeddedDataSource()
	{
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:schema.sql").build();
	}
}
