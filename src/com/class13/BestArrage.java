package com.class13;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 */
public class BestArrage {
    static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //暴力方法
    private static int bestArrage(Meeting[] meetings) {
        if (meetings.length == 0 || meetings == null) return 0;
        return process(meetings, 0, 0);
    }

    private static int process(Meeting[] meetings, int timeline, int done) {
        if (meetings.length == 0) return done;
        int max = done;
        for (int i = 0; i < meetings.length; i++) {
            if (meetings[i].start >= timeline) {
                max = Math.max(max, process(copyButExcept(meetings, i), meetings[i].end, done + 1));
            }
        }
        return max;
    }

    //移除第i位数，创建新数组
    private static Meeting[] copyButExcept(Meeting[] meetings, int i) {
        int N = meetings.length;
        Meeting[] res = new Meeting[N - 1];
        int index = 0;
        for (int j = 0; j < N; j++) {
            if (j != i) res[index++] = meetings[j];
        }
        return res;
    }

    private static int process(Meeting[] meetings) {
        if (meetings.length == 0 || meetings == null) return 0;
        //将会议按结束时间排序
        Arrays.sort(meetings, new MeetingComparator());
        int res = 0, timeline = 0;
        // 依次遍历每一个会议，结束时间早的会议先遍历
        for (int i = 0; i < meetings.length; i++) {
//            i会议的开始时间在当前时间后则开始该会议，并将该会议的结束时间赋值给当前时间线
            if (timeline <= meetings[i].start) {
                res++;
                timeline = meetings[i].end;
            }
        }
        return res;
    }

    public static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }

    // for test
    public static Meeting[] generatePrograms(int programSize, int timeMax) {
        Meeting[] ans = new Meeting[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Meeting(r1, r1 + 1);
            } else {
                ans[i] = new Meeting(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Meeting[] programs = generatePrograms(programSize, timeMax);
            if (bestArrage(programs) != process(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
