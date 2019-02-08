package com.vfreiman.spring.springexamples;

import com.vfreiman.spring.springexamples.beans.TestBean;
import com.vfreiman.spring.springexamples.beans.TestBean2;
import com.vfreiman.spring.springexamples.beans.qualifiers.B;
import com.vfreiman.spring.springexamples.jdbcteamplate.BannerDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyAppConfiguration.class)) {
            TestBean testBean = context.getBean(TestBean.class, "testBean");
            testBean.print();

            TestBean2 testBean2 = context.getBean(TestBean2.class, "testBean2");
            testBean2.print();

            BannerDAO bannerDAO = context.getBean(BannerDAO.class, "bannerDAO");
            bannerDAO.getAll().forEach(System.out::println);

            B b = context.getBean(B.class, "b");
            b.method();
        }
    }
}
