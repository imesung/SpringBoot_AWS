package com.naver;

public class Naver2 {
    public static void main(String[] args) {
        //1000, 1,000
        long val = 1000000;
        String str = String.valueOf(val);

        String result = "";
        int cnt = 0;
        for(int i = str.length()-1; i >= 0; i--) {
            if(cnt % 3 == 0 && cnt != 0) {
                result += ",";
            }
            result += str.charAt(i);
            cnt++;
        }

        String retVal = "";
        for(int i = result.length()-1; i >= 0; i--) {
            retVal += result.charAt(i);
        }

        System.out.println(retVal);

    }
}
