package com.naver;

public class IntegerMax2 {
    public int solution(int n) {
        if (n == 0) {
            return 50;
        }

        char[] charArr = String.valueOf(n).toCharArray();
        StringBuffer result = new StringBuffer(String.valueOf(n));
        boolean isChange = false;
        boolean hasMinus = false;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '-') {
                hasMinus = true;
                continue;
            }

            if (hasMinus) {
                if (charArr[i] > '5') {
                    result.insert(i, "5");
                    isChange = true;
                    break;
                }
            }
            else {
                if (charArr[i] < '5') {
                    result.insert(i, "5");
                    isChange = true;
                    break;
                }
            }
        }
        if (!isChange) result.append(5);
        return Integer.valueOf(result.toString());
    }
}
