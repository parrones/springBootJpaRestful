package com.example.demo.infrastructure.repository.jpa;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.example.demo.infrastructure.repository.jpa")
@EnableTransactionManagement
@Import({DataSourceConfiguration.class, DataSourceConfigurationDev.class})
public class JpaContext 
{
	private static final String PACKAGE_TO_SCAN = "com.example.demo.infrastructure.repository.jpa";
	private static final String HIBERNATE_DIALECT_MY_SQL_DIALECT = "org.hibernate.dialect.MySQLDialect";
	private static final String HIBERNATE_DIALECT_H2_DIALECT = "org.hibernate.dialect.H2Dialect";

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean(name="entityManagerFactory")
	@Profile({"test"})
	public LocalContainerEntityManagerFactoryBean entityEmbeddedManagerFactory(DataSource dataSource)
	{
		return createLocalContainerEntityManagerFactoryBean(dataSource, HIBERNATE_DIALECT_H2_DIALECT);
	}
	
	@Bean(name="entityManagerFactory")
	@Profile({"default"})
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
	{	
		return createLocalContainerEntityManagerFactoryBean(dataSource, HIBERNATE_DIALECT_MY_SQL_DIALECT);
	}
	
	private LocalContainerEntityManagerFactoryBean createLocalContainerEntityManagerFactoryBean(DataSource dataSource, String hibernateDialect) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan(PACKAGE_TO_SCAN);
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", HIBERNATE_DIALECT_H2_DIALECT);
		jpaProperties.put("hibernate.show_sql", true);
		entityManagerFactory.setJpaProperties(jpaProperties);
		
		return entityManagerFactory;
	}
}
