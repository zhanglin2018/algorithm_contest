package com.adavantest.algorithm;

import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.tree.TreeNode;

import java.util.Queue;

class Dp {
	public int value;
	public int count;

	Dp() {
		this.value = 0;
		this.count = 0;
	}

}

class Dp1 {
	public int value;
	public List<Integer> counts;

	Dp1() {
		this.value = 0;
		this.counts = new ArrayList<Integer>();
	}

	void addLast(int count) {
		counts.add(count);
	}

	void removeAll() {
		if (!counts.isEmpty()) {
			counts.clear();
		}
	}

	@Override
	public String toString() {
		return "Dp1 [value=" + value + ", counts=" + counts + "]";
	}
}

class TaskNode {
	public TaskNode parent;
	public List<TaskNode> childrens;
	public List<Integer> values;
	public static List<List<List<Integer>>> paths = new ArrayList<List<List<Integer>>>();
	public static List<List<Integer>> paths1 = new ArrayList<List<Integer>>();

	public TaskNode() {
		parent = null;
		childrens = new ArrayList<TaskNode>();
		values = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return "TaskNode [values=" + values + "]";
	}

	public static List<List<List<Integer>>> getTreeAllPaths(TaskNode treeNode, List<List<Integer>> path) {
		if (!paths.isEmpty()) {
			paths.clear();
		}

		List<List<List<Integer>>> resultList = new ArrayList<List<List<Integer>>>();
		getTreeAllPathsImp(treeNode, path);

		for (List<List<Integer>> value : paths) {
			resultList.add(value);
		}

		return resultList;
	}

	private static List<List<List<Integer>>> getTreeAllPathsImp(TaskNode treeNode, List<List<Integer>> path) {

		if (treeNode == null || path == null) {
			return paths;
		}

		List<List<Integer>> pathTmp = new ArrayList<List<Integer>>();
		pathTmp.addAll(path);
		pathTmp.add(treeNode.values);

		if (treeNode.childrens.isEmpty()) {
			paths.add(pathTmp);
			return paths;
		}

		for (TaskNode node : treeNode.childrens) {
			getTreeAllPathsImp(node, pathTmp);
		}
		return paths;
	}

	public static List<List<Integer>> getTreeAllPaths1(TaskNode treeNode, List<Integer> path) {
		if (!paths1.isEmpty()) {
			paths1.clear();
		}

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		getTreeAllPathsImp1(treeNode, path);

		for (List<Integer> value : paths1) {
			resultList.add(value);
		}

		return resultList;
	}

	private static List<List<Integer>> getTreeAllPathsImp1(TaskNode treeNode, List<Integer> path) {

		if (treeNode == null || path == null) {
			return paths1;
		}

		List<Integer> pathTmp = new ArrayList<Integer>();
		pathTmp.addAll(path);
		pathTmp.addAll(treeNode.values);

		if (treeNode.childrens.isEmpty()) {
			paths1.add(pathTmp);
			return paths1;
		}

		for (TaskNode node : treeNode.childrens) {
			getTreeAllPathsImp1(node, pathTmp);
		}
		return paths1;
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

		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
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

		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
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
		Map<Integer, Integer> selected = new LinkedHashMap<>();

		for (int i = taskWeight.size(); i >= 1; i--) {
			if (dp[i][j].value > dp[i - 1][j].value) {
				selected.put(i - 1, dp[i][j].count);
				j = j - dp[i][j].count * taskWeight.get(i - 1);
			}
		}

		return selected;
	}

	/*
	 * optimized DP algorithm
	 */
	public static List<List<List<Integer>>> getALLMostOptimizedSolutions(List<Integer> taskWeight,
			List<Integer> taskCount, int days) {
		if (taskWeight == null || taskWeight.isEmpty() || taskCount == null || taskCount.isEmpty() || days < 0) {
			return null;
		}

		Dp1 dp[][] = new Dp1[taskWeight.size() + 1][days + 1];

		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
				dp[i][j] = new Dp1();
			}
		}

		for (int i = 1; i <= taskWeight.size(); ++i) {
			for (int j = 1; j <= days; ++j) {
				if (j < taskWeight.get(i - 1)) {
					dp[i][j].value = dp[i - 1][j].value;
					dp[i][j].addLast(0);
				} else {
					int value = 0;

					for (int k = 0; k * taskWeight.get(i - 1) <= j && k <= taskCount.get(i - 1); k++) {
						value = dp[i - 1][j - k * taskWeight.get(i - 1)].value + k * taskWeight.get(i - 1);

						if (dp[i][j].value < value) {
							dp[i][j].value = value;
							dp[i][j].removeAll();
							dp[i][j].addLast(k);
						} else if (dp[i][j].value == value) {
							dp[i][j].addLast(k);
						}

					}

				}
			}
		}

		/*
		 * 测试 矩阵
		 *
		 * for (int i = 0; i <= taskWeight.size(); ++i) { for (int j = 0; j <= days;
		 * ++j) { System.out.print(dp[i][j].value + " "); } System.out.println(); }
		 * 
		 * for (int i = 0; i <= taskWeight.size(); ++i) { for (int j = 0; j <= days;
		 * ++j) { System.out.print(dp[i][j].counts + " "); } System.out.println(); }
		 * 
		 * /* end
		 */

		TaskNode rootNode = new TaskNode();
		fun1(dp, taskWeight.size(), days, taskWeight, taskCount, rootNode);

		List<List<List<Integer>>> allSolutions = TaskNode.getTreeAllPaths(rootNode, new ArrayList<List<Integer>>());

		List<List<List<Integer>>> selected = getMostBasSolution(allSolutions, taskWeight, taskCount);

		return selected;
	}

	@SuppressWarnings("boxing")
	private static List<List<List<Integer>>> getMostBasSolution(List<List<List<Integer>>> allSolutions,
			List<Integer> taskWeight, List<Integer> taskCount) {
		if (allSolutions == null || taskWeight == null || taskCount == null) {
			return null;
		}

		int buttom[] = new int[MAX_DAYS];
		double buttomProblem[] = new double[MAX_DAYS];
		double meanValue[] = new double[MAX_DAYS];
		int countsSum = 0;

		for (List<List<Integer>> singleSolution : allSolutions) {
			List<Integer> itegrationList = new ArrayList<>();
			for (List<Integer> value : singleSolution) {
				itegrationList.addAll(value);
			}

			for (Integer value : itegrationList) {
				buttom[value]++;
				countsSum++;
			}

		}
		
		for (int i=0; i<buttom.length; ++i) {
			buttomProblem[i] = buttom[i] / (double)countsSum;
		} 
		
		Map<List<List<Integer>>, Double> meanValueCache = new HashMap<>();
		
		double allmeanSum = 0.0;
		for (List<List<Integer>> singleSolution : allSolutions) {
			List<Integer> itegrationList = new ArrayList<>();
			for (List<Integer> value : singleSolution) {
				itegrationList.addAll(value);
			}
			
		    double singleSum = 0.0;
			for (Integer value : itegrationList) {
				singleSum += value * buttomProblem[value];
			}
			
			allmeanSum += singleSum;
			meanValueCache.put(singleSolution, singleSum);
		}
		
		double allSolutionMean = allmeanSum / allSolutions.size();
		
		List<Map.Entry<List<List<Integer>>, Double>> listDataEntries = new ArrayList<>(meanValueCache.entrySet());
		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Double>>() {
			@Override
			public int compare(Entry<List<List<Integer>>, Double> o1, Entry<List<List<Integer>>, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		//case 1
//		List<List<List<Integer>>> selected = new ArrayList<>();
//		for (Map.Entry<List<List<Integer>>, Double> entry : listDataEntries) {
//			if (entry.getValue() <= allSolutionMean && selected.size() <= 5) {
//				selected.add(entry.getKey());
//			}else {
//				break;
//			}
//		}
		
//		 case 2
		List<List<List<Integer>>> selected = new ArrayList<>();
		for (Map.Entry<List<List<Integer>>, Double> entry : listDataEntries) {
			selected.add(entry.getKey());
			break;
		}
		
		// case 2
//		List<List<List<Integer>>> selected = new ArrayList<>();
//		selected.add(listDataEntries.get(listDataEntries.size()-1).getKey());
		
		
		
		return selected;
	}

	private static double getProblemRate(List<Integer> expectedValue, List<Integer> taskWeight,
			List<Integer> taskCount) {
		return 0.0;
	}

	/*
	 * optimized DP algorithm
	 */
	public static List<List<List<Integer>>> getALLMostOptimizedSolutions1(List<Integer> taskWeight,
			List<Integer> taskCount, int days) {
		if (taskWeight == null || taskWeight.isEmpty() || taskCount == null || taskCount.isEmpty() || days < 0) {
			return null;
		}

		Dp1 dp[][] = new Dp1[taskWeight.size() + 1][days + 1];

		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
				dp[i][j] = new Dp1();
			}
		}

		for (int i = 1; i <= taskWeight.size(); ++i) {
			for (int j = 1; j <= days; ++j) {
				if (j < taskWeight.get(i - 1)) {
					dp[i][j].value = dp[i - 1][j].value;
					dp[i][j].addLast(0);
				} else {
					int value = 0;

					for (int k = 0; k * taskWeight.get(i - 1) <= j && k <= taskCount.get(i - 1); k++) {
						value = dp[i - 1][j - k * taskWeight.get(i - 1)].value + k * taskWeight.get(i - 1);

						if (dp[i][j].value < value) {
							dp[i][j].value = value;
							dp[i][j].removeAll();
							dp[i][j].addLast(k);
						} else if (dp[i][j].value == value) {
							dp[i][j].addLast(k);
						}

					}

				}
			}
		}

		/*
		 * 测试 矩阵
		 *
		 * for (int i = 0; i <= taskWeight.size(); ++i) { for (int j = 0; j <= days;
		 * ++j) { System.out.print(dp[i][j].value + " "); } System.out.println(); }
		 * 
		 * for (int i = 0; i <= taskWeight.size(); ++i) { for (int j = 0; j <= days;
		 * ++j) { System.out.print(dp[i][j].counts + " "); } System.out.println(); }
		 * 
		 * /* end
		 */

		TaskNode rootNode = new TaskNode();
		fun1(dp, taskWeight.size(), days, taskWeight, taskCount, rootNode);

		List<List<List<Integer>>> allSolutions = TaskNode.getTreeAllPaths(rootNode, new ArrayList<List<Integer>>());

		return allSolutions;
	}

	public static void fun1(Dp1[][] valueMatrix, int row, int column, List<Integer> taskWeight, List<Integer> taskCount,
			TaskNode results) {
		if (valueMatrix == null || row < 0 || column < 0 || taskWeight == null || taskCount == null
				|| results == null) {
			return;
		}

		if (valueMatrix[row][column].counts.isEmpty()) {
			return;
		}

		for (Integer count : valueMatrix[row][column].counts) {
			if (count == 0) {
//				TaskNode taskNode = new TaskNode();
//				results.childrens.add(taskNode);
//				taskNode.parent = results;
				fun1(valueMatrix, row - 1, column, taskWeight, taskCount, results);

			} else {
				TaskNode taskNode = new TaskNode();
				results.childrens.add(taskNode);
				taskNode.parent = results;

				int indexCount = count;

				while (indexCount > 0) {
					taskNode.values.add(taskWeight.get(row - 1));
					indexCount--;
				}

				fun1(valueMatrix, row - 1, column - count * taskWeight.get(row - 1), taskWeight, taskCount, taskNode);
			}
		}
	}

	@SuppressWarnings("boxing")
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

		TaskNode rootNode = new TaskNode();
		initializeSolutionTree(tasksWeight, tasksCount, days, rootNode);

		minBox = getOptimiedBoxNum(rootNode);
		return minBox;
	}

	private static int getOptimiedBoxNum(TaskNode rootNode) {
		int minBox;
		List<List<List<Integer>>> allSolutions = TaskNode.getTreeAllPaths(rootNode, new ArrayList<List<Integer>>());

//		System.out.println(allSolutions);

		Deque<List<List<Integer>>> optimizedSolutionsQueue = new LinkedList<List<List<Integer>>>();
		optimizedSolutionsQueue.addLast(allSolutions.get(0));

		for (List<List<Integer>> singleSolution : allSolutions) {
			while (!optimizedSolutionsQueue.isEmpty()) {
				if (optimizedSolutionsQueue.peekLast().size() > singleSolution.size()) {
					optimizedSolutionsQueue.pollLast();
				} else {
					break;
				}
			}

			if (optimizedSolutionsQueue.isEmpty()
					|| optimizedSolutionsQueue.peekLast().size() == singleSolution.size()) {
				optimizedSolutionsQueue.addLast(singleSolution);
			}

		}

		minBox = optimizedSolutionsQueue.getFirst().size() - 1;
		
		//print
		List<List<Integer>> valuesList = optimizedSolutionsQueue.getFirst();
		for (List<Integer> values : valuesList) {
			for (Integer value : values) {
				System.out.print(value + " ");
			}
			System.out.println();
		}
		
		return minBox;
	}

	public static void initializeSolutionTree(List<Integer> tasksWeight, List<Integer> tasksCount, int days,
			TaskNode parentNode) {
		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days <= 0
				|| parentNode == null) {
			return;
		}

		List<List<List<Integer>>> allOptimizedSolutions = getALLMostOptimizedSolutions(tasksWeight, tasksCount, days);

		for (List<List<Integer>> singleSolution : allOptimizedSolutions) {
			List<Integer> itegrationList = new ArrayList<>();
			for (List<Integer> value : singleSolution) {
				itegrationList.addAll(value);
			}

			TaskNode childNode = new TaskNode();
			childNode.parent = parentNode;
			parentNode.childrens.add(childNode);
			childNode.values.addAll(itegrationList);

			List<Integer> tasksWeightTmp = new ArrayList<>();
			tasksWeightTmp.addAll(tasksWeight);
			List<Integer> tasksCountTmp = new ArrayList<>();
			tasksCountTmp.addAll(tasksCount);

			if (updateTaskList(tasksWeightTmp, tasksCountTmp, itegrationList)) {
				initializeSolutionTree(tasksWeightTmp, tasksCountTmp, days, childNode);
			}
		}

	}

	@SuppressWarnings("boxing")
	public static boolean updateTaskList(List<Integer> tasksWeight, List<Integer> tasksCount,
			List<Integer> expectedValue) {
		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty()
				|| expectedValue == null || expectedValue.isEmpty()) {
			return false;
		}

		for (Integer value : expectedValue) {
			int index = tasksWeight.indexOf(value);
			if (index == -1) {
				return false;
			}
			tasksCount.set(index, tasksCount.get(index) - 1);
		}

		for (int i = tasksCount.size() - 1; i >= 0; --i) {
			if (tasksCount.get(i) == 0) {
				tasksWeight.remove(i);
				tasksCount.remove(i);
			}
		}

		return true;
	}

	public static int get_people_num1(int days, List<Integer> tasks) {
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
			Map<Integer, Integer> selected = algorithm2(tasksWeight, tasksCount, days);
			if (selected == null || selected.isEmpty()) {
				break;
			}

			List<Integer> singleSolution = new ArrayList<>();

			for (Entry<Integer, Integer> entry : selected.entrySet()) {
				int index = entry.getKey();

				int compare = tasksCount.get(index) - entry.getValue();

				/*
				 * store single solution
				 */
				for (int i = 1; i <= entry.getValue(); ++i) {
					singleSolution.add(tasksWeight.get(index));
				}

				if (compare == 0) {
					tasksCount.remove(index);
					tasksWeight.remove(index);
				} else {
					tasksCount.set(index, compare);
				}

			}

			if (tasksWeight.isEmpty() || tasksCount.isEmpty()) {
				minBox++;
				solutionsMap.put(minBox, singleSolution);
				break;
			}

			minBox++;
			solutionsMap.put(minBox, singleSolution);
		}

		for (List<Integer> solutions : solutionsMap.values()) {
			for (int i = solutions.size() - 1; i >= 0; --i) {
				System.out.print(solutions.get(i) + " ");
			}
			System.out.println();
		}

		return minBox;
	}

	public static void main(String[] args) {
		int days = 10;
		List<Integer> tasks = Arrays.asList(new Integer[] { 6, 3, 3, 2, 2, 2, 2 });
		int minBox = Algorithm.get_people_num1(days, tasks);
	}
}
