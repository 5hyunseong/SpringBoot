package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Service;

@Service
public class MenuService {

    public void afterMethod() {
        System.out.println("[MenuService] 메소드 호출 확인..");
    }
}
