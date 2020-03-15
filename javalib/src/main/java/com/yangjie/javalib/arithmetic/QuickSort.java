package com.yangjie.javalib.arithmetic;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快排
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {70, 65, 34, 82, 53, 25, 90};
        System.out.println(Arrays.toString(arr));
        GenerQuickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    static int i = 0;

    private static void quickSort(int[] arr, int low, int high) {

        if (low < high) {//递归头，结束递归，防止栈溢出
            int middle = getPartition(arr, low, high);//拿到基准元素位置
            i++;
            System.out.println(i + "排序是" + Arrays.toString(arr));
            quickSort(arr, 0, middle - 1);//排序左边
            quickSort(arr, middle + 1, high);//排序右边
        }
    }

    public static int[] GenerQuickSort(int[] arr, int left, int right) {
        //定义一个栈
        Stack<Integer> stack = new Stack<>();
        //前提条件就是left < right
        if (left < right) {
            stack.push(left);  //将left压进栈中
            stack.push(right);  //将right压进栈中
            while (!stack.isEmpty()) {  //当栈不为空时，进行以后的操作
                int height = stack.pop();  //取出栈中第一位，我最后压进的是right，所以它是最末位的下标；
                int low = stack.pop();  //最底下的是left的下标。到底哪个对应那个，要看你是怎么压进去的

                int index = getPartition(arr, low, height);  //得到基准数的下标
                if (index - 1 > low) {   //判断基准数左边的数的下标与开始下标的大小
                    stack.push(low);    //根据情况压进去以基准数分开的下标的左半边部分的低位
                    stack.push(index - 1);   //根据情况压进去以基准数分开的下标的左半边部分的高位
                }

                if (index + 1 < height) {  //判断基准数右边的数的下标与开始下标的大小
                    stack.push(index + 1);   //根据情况压进去以基准数分开的下标的右半边部分的低位
                    stack.push(height);   //根据情况压进去以基准数分开的下标的右半边部分的高位
                }
                i++;
                System.out.println(i + "排序是" + Arrays.toString(arr));
                System.out.println(i + "栈是啥" + stack.toString());
            }
        }
        return arr;
    }


    private static int getPartition(int[] arr, int low, int high) {
        System.out.println("基准小标" + low);
        System.out.println("基准大标" + high);
        int temp = arr[low];//确定基准元素
        //从右边找比基准元素小的元素  放到最左侧
        while (low < high) {
            while (low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];
            //从左边找到比基准元素大的元素 放到最右侧
            while (low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        //此时low==high
        //基准元素放到中间
        arr[low] = temp;
        System.out.println("返回" + low);
        return low;

    }


}
