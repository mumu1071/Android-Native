package com.yangjie.javalib.arithmetic;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/15 10:36 PM
 */
public class TestString {

    public static void main(String[] args) {

        findLCS();
    }

    //最长公共子序列
    public static int findLCS() {
        String a = "ab34c", b = "a1bc2";
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%-6d", dp[i][j]);
            }
        }

        System.out.println( dp[n][m]);

        return dp[n][m];
    }

}
