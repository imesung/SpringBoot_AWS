package com.naver;

public class Main {
    public static void main(String[] args) {
        EnumTest gender;
        gender = EnumTest.FEMALE;
        System.out.println(gender + "abc");


        FinalTest finalTest = new FinalTest();
        String str = FinalTest.MALE;
        str = "female";
    }
}
