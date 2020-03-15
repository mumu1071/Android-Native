package arithmetic;


import java.util.Arrays;

/**
 * 动态规划找零钱
 */
public class DpCoins {
    static class SolutionA {

        public int coinChange(int[] coins, int amount) {
            return coinChange(0, coins, amount);
        }

        private int coinChange(int idxCoin, int[] coins, int amount) {
            if (amount == 0)
                return 0;
            if (idxCoin < coins.length && amount > 0) {
                int maxVal = amount / coins[idxCoin];
                int minCost = Integer.MAX_VALUE;
                for (int x = 0; x <= maxVal; x++) {
                    if (amount >= x * coins[idxCoin]) {
                        int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                        if (res != -1)
                            minCost = Math.min(minCost, res + x);
                    }
                }
                return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
            }
            return -1;
        }
    }

    //备忘录 自上而下
    static class SolutionB {

        public int coinChange(int[] coins, int amount) {
            if (amount < 1) return 0;
            return coinChange(coins, amount, new int[amount]);
        }

        private int coinChange(int[] coins, int amount, int[] count) {
            if (amount < 0) return -1;
            if (amount == 0) return 0;

            if (count[amount - 1] != 0) return count[amount - 1];

            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChange(coins, amount - coin, count);
                if (res >= 0 && res < min)
                    min = 1 + res;
            }
            count[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[amount - 1];
        }

    }


    //dp table 自下而上
    static class SolutionC {
        public int coinChange(int[] coins, int amount) {
            int max = amount + 1;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }




    public static void main(String[] args) {
        SolutionB solution = new SolutionB();
        // 硬币面值预先已经按降序排列
        int[] coinValue = new int[]{5, 21, 10, 25, 1};
        // 需要找零的面值
        int money = 63;
        int a = solution.coinChange(coinValue, money);
        System.out.println(a);
    }
}
