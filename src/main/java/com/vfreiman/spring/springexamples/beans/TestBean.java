package com.vfreiman.spring.springexamples.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("testBean")
@Qualifier("testBean")
@Scope("singleton")
public class TestBean {
    public void print() {
        System.out.println("testBean");
    }
}
