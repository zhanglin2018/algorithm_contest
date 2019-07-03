package com.advantest.algorithm.draft;

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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Dp {
    public int value;
    public int count;
    
    Dp() {
        this.value = 0;
        this.count = 0;
    }
}
class Result {

    /*
     * Complete the 'get_people_num' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER days
     *  2. INTEGER_ARRAY tasks
     */

    public static final int MAX_DAYS = 101;

     public static Map<Integer, Integer> algorithm2(List<Integer> taskWeight, List<Integer> taskCount, int days) {
        Dp dp[][] = new Dp[taskWeight.size() + 1][days + 1];
        
        for (int i = 0; i <= taskWeight.size(); ++i){
            for (int j = 0; j <= days; ++j){
                dp[i][j] = new Dp();
            }
        }
        
        int s = 0;
        
        for (int i = 1; i <= taskWeight.size(); ++i) {
            for (int j = 1; j <= days; ++j) {
                if (j - taskWeight.get(i - 1) < 0)
                    dp[i][j].value = dp[i - 1][j].value;
                else {
                    s = dp[i - 1][j].value;
                    int value = 0;
                    
                    for (int k = 1; k * taskWeight.get(i - 1) <= j && k <= taskCount.get(i - 1); k++) {
                        value = dp[i - 1][j - k * taskWeight.get(i - 1)].value + k * taskWeight.get(i - 1);
                        if (s <= value) {
                            dp[i][j].count = k;
                            s = value;
                        }
                    }
                    
                    dp[i][j].value = s;
//                    System.out.println(i + "." + j + " = " + dp[i][j].count);
                    
                }
            }
        }
        
        int j = days;
        Map<Integer, Integer>selected = new LinkedHashMap<>();
        
        for (int i = taskWeight.size(); i >= 1; i--) {
            if (dp[i][j].value > dp[i - 1][j].value) {
                selected.put(i-1, dp[i][j].count);
                j = j - dp[i][j].count * taskWeight.get(i - 1);
            }
        }
        
        return selected;
    }

    public static int get_people_num(int days, List<Integer> tasks) {
    // Write your code here
       if (days <= 0 || tasks == null || tasks.isEmpty()) {
            return -1;
        }

        int buttom[] = new int[MAX_DAYS];

        for (Integer taskInteger : tasks) {
            if (taskInteger < 0 || taskInteger > days) {
                return -1;
            }
            buttom[taskInteger]++;
        }

        List<Integer> tasksCount = new ArrayList<>();
        List<Integer> tasksWeight = new ArrayList<>();
        for (int i = 0; i < buttom.length; ++i) {
            if (buttom[i] != 0) {
                tasksCount.add(buttom[i]);
                tasksWeight.add(i);
            }
        }

        int minBox = 0;
        
        while (true) {
            Map<Integer, Integer> selected = algorithm2(tasksWeight, tasksCount, days);
            if (selected.isEmpty()){
                break;
            }
            
            for (Entry<Integer, Integer> entry : selected.entrySet()){
                int index = entry.getKey();
                
                int compare = tasksCount.get(index) - entry.getValue();
                
                if (compare == 0){
                    tasksCount.remove(index);
                    tasksWeight.remove(index);
                }else{
                    tasksCount.set(index, compare);
                }
                
            }
            
            if (tasksWeight.isEmpty() || tasksCount.isEmpty()){
                minBox++;
                break;
            }
            
            minBox++;
        }

        return minBox;
    

    }

}

public class Draft {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int days = Integer.parseInt(bufferedReader.readLine().trim());

        int tasksCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> tasks = IntStream.range(0, tasksCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.get_people_num(days, tasks);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
