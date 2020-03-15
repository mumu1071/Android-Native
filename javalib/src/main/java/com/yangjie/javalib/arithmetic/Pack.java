package com.yangjie.javalib.arithmetic;
/**
 * @author yangjie
 * @date 2020-03-15 21:44
 * Description: No Description
 */


/*
 *
最多可以放8公斤的物品
物品1:6公斤   价值48元
物品2:1公斤   价值7元
物品3:5公斤   价值40元
物品4:2公斤   价值12元
物品5:1公斤   价值8元

      0  1  2  3  4  5   6    7    8
      0  0  0  0  0  0   0    0    0
6 48  0  0  0  0  0  0   48  48   48
1 7   0  7  7  7  7  7   48  55   55
5 40  0  7  7  7  7  40  48  55   55
2 12  0  7  12 19 19 40  48  55   60
1 8   0  8  15 20 27 40  48  56   63

 */
public class Pack {
    public static void main(String[] args) {
        //定义一个数组a[i][j] = a[i]                 i表示当前物品的序号选上，j表示这个位置能得到的最大值
        //选或者不选动态规划
        int[] w = {0, 6, 1, 5, 2, 1};//表示每件物品的重量
        int[] v = {0, 48, 7, 40, 12, 8};//表示每件物品的价值
        int[][] temp = new int[6][9];   //8表示背包最多能放8公斤的重量
        for (int j = 0; j < 9; j++) {  //初始化每一行
            temp[0][j] = 0;
        }
        for (int i = 1; i < 6; i++) {  //背包的重量为0的时候，最大价值肯定是0
            temp[i][0] = 0;
        }
        for (int i = 1; i < 6; i++) {  //从第一个物品开始选，记录我选了前面出现的物品，背包重量从1-8的能选上的最大的值
            for (int j = 1; j < 9; j++) { //当i循环到最后一层5的时候，也就是得到了，我5件物品都选上的时候的最大的值
                if (w[i] <= j) { //重量比这个状态小，那么就能放。 那么就只是放与不放，看是放重量大，还是不放重量大
                    temp[i][j] = Math.max(temp[i - 1][j], temp[i - 1][j - w[i]] + v[i]);
                } else {
                    temp[i][j] = temp[i - 1][j];//第i件物品不能放
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.printf("%-5d", temp[i][j]);
            }
            System.out.println();
        }
    }
}