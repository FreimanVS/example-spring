package com.vfreiman.spring.springexamples.beans.qualifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("b")
@Qualifier("b")
@Scope("singleton")
public class B {

    @Autowired
    @Qualifier("q1")
//    @Qualifier("q2")
    A a;

    public void method() {
        a.method();
    }
}
