package com.vfreiman.spring.springexamples;

import com.vfreiman.spring.springexamples.beans.TestBean2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration  //The @Configuration annotation indicates to Spring that this is a configuration class
                //that will provide beans to the Spring application context
@ComponentScan(value={"com.vfreiman.spring.springexamples"})
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class MyAppConfiguration {

    @Autowired
    private Environment environment;

    @Bean("testBean2")
    @Qualifier("testBean2")
    @Scope("singleton")
//    @Scope("prototype")
    public TestBean2 testBean2() {
        return new TestBean2();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("datasource.driver"));
        driverManagerDataSource.setUrl(environment.getProperty("datasource.url"));
        driverManagerDataSource.setUsername(environment.getProperty("datasource.user"));
        driverManagerDataSource.setPassword(environment.getProperty("datasource.password"));
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
