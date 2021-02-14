package com.naver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Naver {
    public static void main(String[] args) {
        int [] listArr = {10, 11, 12, 20, 21, 23, 30, 31, 32};
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < listArr.length; i++ ){
            list.add(listArr[i]);
        }
        System.out.println(list.stream().filter(i -> i/10 == 1).collect(Collectors.toList()));
        System.out.println(list.stream().filter(i -> i/10 == 2).collect(Collectors.toList()));
        System.out.println(list.stream().filter(i -> i/10 == 3).collect(Collectors.toList()));

    }
}
