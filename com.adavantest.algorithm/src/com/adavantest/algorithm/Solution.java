package com.adavantest.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


import static java.util.stream.Collectors.toList;

public class Solution {
    public static List<Integer> main(String[] args) throws IOException {
    	String filePath = "resources/test";
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(filePath));
        
        int days = Integer.parseInt(bufferedReader2.readLine().trim());
        int tasksCout = Integer.parseInt(bufferedReader2.readLine().trim());
        
        List<Integer> tasksIntegers = new ArrayList<Integer>();
        
        while(true){
        	String str = bufferedReader2.readLine();
        	if (str == null) {
        		break;
        	}
        	tasksIntegers.add(Integer.parseInt(str));
        }


        bufferedReader2.close();
        return tasksIntegers;
    }
}
