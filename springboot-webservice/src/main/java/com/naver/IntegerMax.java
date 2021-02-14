package com.naver;

public class IntegerMax {

    public static void main(String[] args) {
        System.out.println("268 : " + solution(268));
        System.out.println("670 : " + solution(670));
        System.out.println("0 : " + solution(0));
        System.out.println("17 : " + solution(17));
        System.out.println("750 : " + solution(750));
        System.out.println("-999 : " + solution(-999));
        System.out.println("-55 : " + solution(-55));
        System.out.println("-45 : " + solution(-45));
        System.out.println("-400 : " + solution(-400));


    }

    public static int solution(int N) {
        String chkInt = String.valueOf(N);
        String retVal = "";
        boolean chkVal = true;
        boolean chkSign = true;
        for(int i = 0; i < chkInt.length(); i++) {

            if(chkInt.charAt(i) == '-') {
                chkSign = false;
                continue;
            }

            if(chkInt.charAt(i)-48 < 5 && chkVal) {
                retVal += "5" + chkInt.charAt(i);
                chkVal = false;
                continue;
            }
            retVal += String.valueOf(chkInt.charAt(i));
        }

        //5가 안들어감
        if(chkVal) {
            if(!chkSign) {
                retVal = "-5" + retVal;
            } else {
                retVal = retVal + "5";
            }
        } else {
            if(!chkSign) {
                retVal = "-" + retVal;
            }
        }

        return Integer.parseInt(retVal);
    }
}
