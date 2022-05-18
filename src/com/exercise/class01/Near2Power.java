package com.exercise.class01;

/**
 * @author tian.yang
 * @description
 * @date 2022/4/2 16:05
 */
public class Near2Power {
    public static void main(String[] args) {
        System.out.println(process(0));
    }

    /**
     * 给定一个非负整数num，如果不用循环语句，
     * 返回>=num，并且离num最近的，2的某次方
     *
     * @param num
     * @return
     */
    public static int process(int num) {
//        不用判断0这种特殊情况，为了兼容0，-1则为负数了
        num--;
//            将1后面的0全部变为1
//        本身或上右移1位，>>>无符号右移，整型就32位
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        return num < 0 ? 1 : num + 1;
    }
}
