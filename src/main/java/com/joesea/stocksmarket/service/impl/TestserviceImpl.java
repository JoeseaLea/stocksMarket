package com.joesea.stocksmarket.service.impl;

import com.joesea.stocksmarket.service.Testservice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : </p>
 */
@Service
public class TestserviceImpl implements Testservice {

//    @Value("${joesea.ip}")
    private String ip;

    @Override
    public void printvalue() {
//        System.out.println(ip);
    }
}
