package com.ohgiraffers.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringConfiguration {

    @Value("${test.value}")
    public String value;
    @Value("${test.age}")
    public String age;
    @Value("${test.hobby}")
    public String[] hobby;

    @Bean
    public Object propertiesReadTest() {

        System.out.println("value = " + value);
        System.out.println("age = " + age);
        System.out.println("hobby = " + hobby); // 출력이 예쁘게 안나옴

        System.out.println("===== 예쁘게 출력 =====");
        Arrays.stream(hobby).forEach(System.out::println);

        return new Object();
    }
}
