package com.js.qa.qatools.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(
        // Repository Path
        basePackages = "com.js.qa.qatools.hr.repository.sqlserver",
        //
        entityManagerFactoryRef = "sqlServerEntityManager",
        //
        transactionManagerRef = "sqlServerTransactionManager"
)
public class SQL_Server_DatabaseConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean sqlServerEntityManager(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sqlServerDataSource());

        // Entity 패키지 경로
        em.setPackagesToScan(new String[] { "com.js.qa.qatools.hr.entity.sqlserver" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Hibernate 설정
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.sqlserver-datasource")
    public DataSource sqlServerDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager sqlServerTransactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(sqlServerEntityManager().getObject());
        return transactionManager;
    }
}