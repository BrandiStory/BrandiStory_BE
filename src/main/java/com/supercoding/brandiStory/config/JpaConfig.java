package com.supercoding.brandiStory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = {
            "com.supercoding.brandiStory.repository.carts", "com.supercoding.brandiStory.repository.entity",
            "com.supercoding.brandiStory.repository.products", "com.supercoding.brandiStory.repository.roles",
            "com.supercoding.brandiStory.repository.userDetails", "com.supercoding.brandiStory.repository.userPrincipal",
            "com.supercoding.brandiStory.repository.users"
        },
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(
            "com.supercoding.brandiStory.repository.carts", "com.supercoding.brandiStory.repository.entity",
            "com.supercoding.brandiStory.repository.products", "com.supercoding.brandiStory.repository.roles",
            "com.supercoding.brandiStory.repository.userDetails", "com.supercoding.brandiStory.repository.userPrincipal",
            "com.supercoding.brandiStory.repository.users"
        );

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comment", "true");

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return transactionManager;
    }
}
