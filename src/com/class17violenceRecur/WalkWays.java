package com.class17violenceRecur;

public class WalkWays {
    public static void main(String[] args) {
        System.out.println(isPrime(13));
        String str = "If you can dream — and not make dreams your master;   \n" +
                "    If you can think — and not make thoughts your aim;   \n" +
                "If you can meet with Triumph and Disaster\n" +
                "    And treat those two impostors just the same;   \n" +
                "If you can bear to hear the truth you’ve spoken\n" +
                "    Twisted by knaves to make a trap for fools,\n" +
                "Or watch the things you gave your life to, broken,\n" +
                "    And stoop and build ’em up with worn-out tools:";
        str = str.replaceAll("\\s|\n| +", " ");
        str = str.replaceAll(" +", " ");
        str = str.replaceAll("[.,?!:;]", "");
        String[] split = str.split(" ");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }

    //是否是质数
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        //偶数
        if (num % 2 == 0) return false;
        for (int i = 3; i < Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
