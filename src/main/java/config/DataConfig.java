package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.IOException;

@Configuration
@EnableWebMvc
@PropertySource("classpath:properties/environment.properties")
public class DataConfig extends WebMvcConfigurationSupport {

    @Value("${dataSource.driverClassName}")
    private String DRIVER;

    @Value("${dataSource.url}")
    private String URL;

    @Value("${dataSource.username}")
    private String USER;

    @Value("${dataSource.password}")
    private String PASSWORD;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        dataSource.setDriverClassName(DRIVER);
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        Resource configLocation = new ClassPathResource("hibernate.cfg.xml");
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setConfigLocation(configLocation);
        sessionFactoryBean.setPackagesToScan(
                new String[] {"domain.entity" });
        try {
            sessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactoryBean.getObject();
    }

    @Bean
    public HibernateTransactionManager txManager(){
        HibernateTransactionManager txManager =
                new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory());
        return txManager;
    }
}
