package com.github.lotashinski.api.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.lotashinski.api.repository")
@EnableTransactionManagement
public class JpaConfig {

	private static final String POSTGRES_USER = "POSTGRES_USER";
	
	private static final String POSTGRES_PASSWORD = "POSTGRES_PASSWORD";
	
	private static final String POSTGRES_DATABASE = "POSTGRES_DATABASE";
	
	private static final String POSTGRES_HOST = "POSTGRES_HOST";
	
	private static final String POSTGRES_PORT = "POSTGRES_PORT";
	
	@Bean
    public DataSource dataSource() {
		Dotenv de = DotenvUtils.dotenv();
		
	    String user = de.get(POSTGRES_USER);
	    String pass = de.get(POSTGRES_PASSWORD);
	    String database = de.get(POSTGRES_DATABASE);
	    String host = de.get(POSTGRES_HOST, "127.0.0.1");
	    String port = de.get(POSTGRES_PORT, "5432");
	    
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s/%s", host, port, database));
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        
        return dataSource;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.github.lotashinski.api.entity");
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        
        return em;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("jakarta.persistence.schema-generation.database.action", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }
	
}
