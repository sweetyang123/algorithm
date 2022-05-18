package com.class10binarytree;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 * 1时是凹
 * 2时是在1凹的上下分别有个凹和凸，就是凹凹凸
 * 3时是在2的凹凸两个地方分别有凹凸，就是是凹凹凸凹凹凸凸
 * 相当于一颗二叉树，中序遍历就可以了
 */
public class PaperFolding {
    private void process(int i, int N, boolean down) {
        if (i > N) return;
        process(i + 1, N, true);
        System.out.print((down == true ? "凹" : "凸") + " ");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        PaperFolding p = new PaperFolding();
        p.process(1, 3, true);
    }
}
