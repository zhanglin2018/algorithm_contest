package com.adavantest.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

public class Algorithm {
	
	
	public static final int MAX_DAYS = 101;

	public static void algorithm(List<Integer> taskWeight, List<Integer> taskCount, int days) {
		int dp[][] = new int[taskWeight.size() + 1][days + 1];
		int path[][] = new int[taskWeight.size() + 1][days + 1];
		int s = 0;

		for (int i = 1; i <= taskWeight.size(); ++i) {
			for (int j = 1; j <= days; ++j) {
				if (j - taskWeight.get(i - 1) < 0)
					dp[i][j] = dp[i - 1][j];
				else {
					s = dp[i - 1][j];
					for (int k = 1; k * taskWeight.get(i - 1) <= j && k <= taskCount.get(i - 1); k++) {
						s = Math.max(s, dp[i - 1][j - k * taskWeight.get(i - 1)] + k * taskWeight.get(i - 1));
					}
					dp[i][j] = s;
					path[i][j] = 1;
				}
			}
		}

		System.out.println(dp[taskWeight.size()][days]);

		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();

		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
				System.out.print(path[i][j] + " ");
			}
			System.out.println();
		}

		int i = 5, j = 10;
		List<Integer> taskCountstmp = new ArrayList<>();
		taskCountstmp.addAll(taskCount);

		while (i > 0 && j > 0) {
			if (path[i][j] == 1 && taskCountstmp.get(i - 1) >= 0) {
				System.out.println(i + " ");
				j -= taskWeight.get(i - 1);
				int value = taskCountstmp.get(i - 1);
				value -= 1;
			} else
				i--;
		}

		List<Integer> selected = new ArrayList<>();
		j = days;
		i = 0;
		for (i = taskWeight.size(); i >= 1; i--) {
			if (dp[i][j] > dp[i - 1][j]) {
				selected.add(i);
				j = j - taskWeight.get(i - 1);
			}
		}

		System.out.println(selected);

	}

	public static void algorithm1(List<Integer> taskWeight, List<Integer> taskCount, int days) {
		Dp dp[][] = new Dp[taskWeight.size() + 1][days + 1];
		
		for (int i = 0; i <= taskWeight.size(); ++i){
			for (int j = 0; j <= days; ++j){
				dp[i][j] = new Dp();
			}
		}
		
		int path[][] = new int[taskWeight.size() + 1][days + 1];
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
					System.out.println(i + "." + j + " = " + dp[i][j].count);

				}
			}
		}

		System.out.println(dp[taskWeight.size()][days]);

		// for (int i=0; i <= taskWeight.size(); ++i){
		// for (int j=0; j <= days; ++j){
		// System.out.print(dp[i][j] + " ");
		// }
		// System.out.println();
		// }
		//
		System.out.println();
		//
		//
		// for (int i=0; i <= taskWeight.size(); ++i){
		// for (int j=0; j <= days; ++j){
		// System.out.print(path[i][j] + " ");
		// }
		// System.out.println();
		// }
		//
		int i = 5, j = 10;
		// List<Integer> taskCountstmp = new ArrayList<>();
		// taskCountstmp.addAll(taskCount);
		//
		// while (i > 0 && j > 0)
		// {
		// if (path[i][j] == 1 && taskCountstmp.get(i-1) >= 0)
		// {
		// System.out.println(i + " ");
		// j -= taskWeight.get(i-1);
		// int value = taskCountstmp.get(i-1);
		// value -= 1;
		// }
		// else
		// i--;
		// }
		//
		//
		System.out.println("==============");
		List<Integer> selected = new ArrayList<>();
		j = days;
		i = 0;
		for (i = taskWeight.size(); i >= 1; i--) {
			if (dp[i][j].value > dp[i - 1][j].value) {
				selected.add(i);
				System.out.println(i + "." + j);
				j = j - dp[i][j].count * taskWeight.get(i - 1);
			}
		}
		//
		// System.out.println(selected);

	}
	
	
	/*
	 * 第一次 提交版本
	 */
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
//					System.out.println(i + "." + j + " = " + dp[i][j].count);
					
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
	
	/*
	 * optimized DP algorithm
	 */
	public static Map<Integer, Integer> algorithm3(List<Integer> taskWeight, List<Integer> taskCount, int days) {
		if (taskWeight == null || taskWeight.isEmpty() || taskCount == null || taskCount.isEmpty() || days < 0){
			return null;
		}
		
		Dp dp[][] = new Dp[taskWeight.size() + 1][days + 1];
		
		for (int i = 0; i <= taskWeight.size(); ++i){
			for (int j = 0; j <= days; ++j){
				dp[i][j] = new Dp();
			}
		}
		
		int s = 0;
		
		for (int i = 1; i <= taskWeight.size(); ++i) {
			for (int j = 1; j <= days; ++j) {
				if (j < taskWeight.get(i - 1)){
					dp[i][j].value = dp[i - 1][j].value;
				}
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
				}
			}
		}
		
		/*
		 * 测试 矩阵
		 *
		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
				System.out.print(dp[i][j].value + " ");
			}
			System.out.println();
		}
		
		/*
		 * end
		 */
		
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
		Map<Integer, List<Integer>> solutionsMap = new HashMap<Integer, List<Integer>>();
		
		while (true) {
			Map<Integer, Integer> selected = algorithm3(tasksWeight, tasksCount, days);
			if (selected == null || selected.isEmpty()){
				break;
			}
			
			List<Integer> singleSolution = new ArrayList<>();
			
			for (Entry<Integer, Integer> entry : selected.entrySet()){
				int index = entry.getKey();
				
				int compare = tasksCount.get(index) - entry.getValue();

				/*
				 * store single solution
				 */
				for (int i = 1; i <= entry.getValue(); ++i){
					singleSolution.add(tasksWeight.get(index));
				}
				
				if (compare == 0){
					tasksCount.remove(index);
					tasksWeight.remove(index);
				}else{
					tasksCount.set(index, compare);
				}
				
			}
			
			if (tasksWeight.isEmpty() || tasksCount.isEmpty()){
				minBox++;
				solutionsMap.put(minBox, singleSolution);
				break;
			}
			
			minBox++;
			solutionsMap.put(minBox, singleSolution);
		}
		
		for (List<Integer> solutions : solutionsMap.values()){
			for (int i = solutions.size() - 1; i >= 0; --i){
				System.out.print(solutions.get(i) + " ");
			}
			System.out.println();
		}

		return minBox;
	}

	public static void main(String[] args) {
		int days = 10;
		List<Integer> tasks = Arrays.asList(new Integer[] { 6, 3, 3, 2, 2, 2, 2 });
		int minBox = Algorithm.get_people_num(days, tasks);
	}
}
