package by.zhigalko.snow.world.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Configuration
@ComponentScan("by.zhigalko.snow.world")
@PropertySource("file:/opt/application.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "by.zhigalko.snow.world.repository")
public class ApplicationConfig {
    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
        log.debug("Environment properties initialized");
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("jdbc.driver")));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        log.debug("DriverManager initialized");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("by.zhigalko.snow.world.entity");
        factory.setDataSource(driverManagerDataSource());
        factory.setJpaProperties(hibernateProperties());
        log.debug("EntityManagerFactory initialized");
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        log.debug("TransactionManager initialized");
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        log.debug("ExceptionTranslation initialized");
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public MinioClient minioClient() {
        log.debug("MinioClient is initializing");
        return MinioClient.builder()
                .endpoint(Objects.requireNonNull(env.getProperty("minio.url")))
                .credentials(Objects.requireNonNull(env.getProperty("minio.username")),
                        Objects.requireNonNull(env.getProperty("minio.password")))
                .build();
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        log.debug("Hibernate initialized");
        return hibernateProperties;
    }
}
