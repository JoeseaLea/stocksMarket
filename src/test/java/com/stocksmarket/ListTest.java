package com.stocksmarket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/12/26</p>
 * <p>@description : </p>
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < 1000; i ++) {
            list.add(i);
        }

//        output(list.subList(0, 10));
//        output(list.subList(3, 10));
//        output(list.subList(0, 10));
//        output(list.subList(0, 10));
//        output(list.subList(0, 10));
        System.out.println(list.get(0));
        output(list.subList(11, 15));
    }

    private static void output(List<Integer> list) {
        for (Integer e : list) {
            System.out.println(e);
        }
    }
}
