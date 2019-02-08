package com.vfreiman.spring.springexamples.beans.qualifiers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("aImpl")
@Qualifier("q1")
@Scope("singleton")
public class AImpl implements A {
    @Override
    public void method() {
        System.out.println("AImpl 1");
    }
}
