package com.vfreiman.spring.springexamples;

import com.vfreiman.spring.springexamples.beans.TestBean2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static com.vfreiman.spring.springexamples.Constants.*;

@Configuration  //The @Configuration annotation indicates to Spring that this is a configuration class
                //that will provide beans to the Spring application context
@ComponentScan(value={"com.vfreiman.spring.springexamples"})
@EnableAspectJAutoProxy
public class MyAppConfiguration {

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
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(USER);
        driverManagerDataSource.setPassword(PASSWORD);
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
