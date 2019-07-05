package com.adavantest.algorithm;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result1 {

    /*
     * Complete the 'get_people_num' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER days
     *  2. INTEGER_ARRAY tasks
     */

    public static int get_people_num(int days, List<Integer> tasks) {
    // Write your code here
    	return 0;
    }
    
    public static void main(String[] args) {
    	/*        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//物品种类个数
        int [] weight = new int[n];//重量
        int [] value = new int[n];//价值
        for (int i = 0; i < n; i++) {
            weight[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            value[i] = in.nextInt();
        }
        int target = in.nextInt();//背包容量*/
    	
        int n = 5;
        int [] weight = {0, 5,4,7,2,6};
        int [] value = {0, 12,3,10,3,6};
        int target = 15;
        int [] account = {0, 2,4,6,8,1};//每种物品的数量
        int[] arr = new int[target + 1];
        
        //依次轮转，把当背包中有一个、二个、三个，，，
        for (int i = 1; i < weight.length; i++) {  // i 表示背包中可以放前（i + 1） 种物品
            // 完全背包问题
//            if(weight[i] * account[i] >= target){
//                for (int j = weight[i]; j <= target; j++) {  
//                    arr[j] = Math.max(arr[j],arr[j - weight[i]] + value[i]);
//                }
//                return;
//            }
            
            //01背包问题，并使用二进制方法进行优化
            int tmpCount = 1;
            while (tmpCount < account[i]){
                for (int j = target; j >= tmpCount * weight[i]; j--) {  // j 表示当前背包的容量
                    arr[j] = Math.max(arr[j],arr[j - tmpCount * weight[i]] + tmpCount * value[i]);
                }
                account[i] -= tmpCount;
                tmpCount = tmpCount << 1;
            }
            
            for (int j = target; j >= weight[i]; j--) {  // j 表示当前背包的容量
                arr[j] = Math.max(arr[j],arr[j - weight[i]] + value[i]);
            }
            
        }
        
        for (int i = 1; i <= target; i++) {
            System.out.print(arr[i] + " ");
        }

    }


}



