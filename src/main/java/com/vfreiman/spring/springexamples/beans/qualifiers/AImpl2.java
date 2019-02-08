package com.vfreiman.spring.springexamples.beans.qualifiers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("aImpl2")
@Qualifier("q2")
@Scope("singleton")
public class AImpl2 implements A {
    @Override
    public void method() {
        System.out.println("AImpl2");
    }
}
