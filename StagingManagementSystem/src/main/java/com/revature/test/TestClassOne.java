package com.revature.test;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:myproperties.properties")
public class TestClassOne{

}
