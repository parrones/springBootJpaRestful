package com.example.demo.infrastructure.repository.jpa;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
	private static final String HIBERNATE_DIALECT_H2 = "org.hibernate.dialect.H2Dialect";
	private static final String PACKAGE_TO_SCAN = "com.example.demo.infrastructure.repository.jpa";
	private static final String HIBERNATE_DIALECT_MY_SQL = "org.hibernate.dialect.MySQLDialect";

	@Autowired
	@Bean
	public JpaTransactionManager transactionManager(AbstractEntityManagerFactoryBean entityManagerFactory)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}
	
	@Bean(name = "entityManagerFactory")
	@Profile({"dev", "default"})
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityEmbeddedManagerFactory(DataSource dataSource)
	{	
		return createLocalContainerEntityManagerFactoryBean(dataSource, HIBERNATE_DIALECT_H2);
	}
	
	@Bean(name = "entityManagerFactory")
	@Profile({"local"})
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFacory(DataSource dataSource)
	{	
		return createLocalContainerEntityManagerFactoryBean(dataSource, HIBERNATE_DIALECT_MY_SQL);
	}
	
	private LocalContainerEntityManagerFactoryBean createLocalContainerEntityManagerFactoryBean(DataSource dataSource, String hibernateDialect)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan(PACKAGE_TO_SCAN);
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.show_sql", true);
		entityManagerFactory.setJpaProperties(jpaProperties);
		
		return entityManagerFactory;
	}
	
	@Bean
	@Profile({"local"})
	@ConfigurationProperties(prefix = "datasource.mysql")
	public DataSource dataSource()
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Profile({"dev", "default"})
	public DataSource emmbeddedDataSource()
	{
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:schema.sql").build();
	}
}
