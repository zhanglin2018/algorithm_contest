package com.adavantest.algorithm;

import java.security.Signature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.sound.midi.Soundbank;

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



interface SelectedCallBack {
	List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions, List<Integer> taskWeight,
			List<Integer> taskCount, int days);

}

class NormalSelected implements SelectedCallBack {

	@Override
	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
			List<Integer> taskWeight, List<Integer> taskCount, int days) {
		if (allSolutions == null || allSolutions.isEmpty()) {
			return allSolutions;
		}

		return allSolutions;
	}

}

class PruneSelected implements SelectedCallBack {
	@SuppressWarnings("boxing")
	@Override
	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
			List<Integer> taskWeight, List<Integer> taskCount, int days) {
		if (allSolutions == null || allSolutions.isEmpty() || taskWeight == null || taskWeight.isEmpty()
				|| taskCount == null || taskCount.isEmpty() || days <= 0) {
			return Collections.emptyList();
		}
		
		if (allSolutions.size() == 1) {
			return allSolutions;
		}
		
		int tasksSum = Result.listSum(Result.convertFormatToList(allSolutions.get(0)));
		
		if (tasksSum < days ){
			return allSolutions;

		}

		Map<List<List<Integer>>, Integer> solutionsCacheMap = new LinkedHashMap<>();

		for (List<List<Integer>> singleSolution : allSolutions) {
			solutionsCacheMap.put(singleSolution, Result.convertFormatToList(singleSolution).size());
		}

		List<Map.Entry<List<List<Integer>>, Integer>> listDataEntries = new ArrayList<>(solutionsCacheMap.entrySet());
		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Integer>>() {

			@Override
			public int compare(Entry<List<List<Integer>>, Integer> o1, Entry<List<List<Integer>>, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		/*
		 * find the most optimized solution
		 */
		int minValue = listDataEntries.get(0).getValue();
		List<List<List<Integer>>> selectedSolution = new ArrayList<>();


		for (Map.Entry<List<List<Integer>>, Integer> entry : listDataEntries) {
			if (entry.getValue() > minValue) {
				break;
			}

			selectedSolution.add(entry.getKey());
		}

		if (selectedSolution.isEmpty()) {
			selectedSolution.add(listDataEntries.get(0).getKey());
		}
		
//		int tasksSum = Result.listSum(Result.convertFormatToList(selectedSolution.get(0)));
//		
//		if (tasksSum < days && (selectedSolution.size() == 1 && listDataEntries.size() > 1)){
////			System.out.println(selectedSolution + " From: " + listDataEntries);
////			selectedSolution.add(listDataEntries.get(1).getKey());
//			return allSolutions;
//
//		}
		
//		if (selectedSolution.size() == 1 && listDataEntries.size() > 1){
//			selectedSolution.add(listDataEntries.get(1).getKey());
//		}
		
		
		// case 1

		return selectedSolution;
	}
}
class PruneSelected2 implements SelectedCallBack {
	@SuppressWarnings("boxing")
	@Override
	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
			List<Integer> taskWeight, List<Integer> taskCount, int days) {
		if (allSolutions == null || allSolutions.isEmpty() || taskWeight == null || taskWeight.isEmpty()
				|| taskCount == null || taskCount.isEmpty() || days <= 0) {
			return Collections.emptyList();
		}
		
		if (allSolutions.size() == 1) {
			return allSolutions;
		}
		
		Map<List<List<Integer>>, Integer> solutionsCacheMap = new HashMap<>();
		
		for (List<List<Integer>> singleSolution : allSolutions) {
			solutionsCacheMap.put(singleSolution, Result.convertFormatToList(singleSolution).size());
		}
		
		List<Map.Entry<List<List<Integer>>, Integer>> listDataEntries = new ArrayList<>(solutionsCacheMap.entrySet());
		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Integer>>() {
			
			@Override
			public int compare(Entry<List<List<Integer>>, Integer> o1, Entry<List<List<Integer>>, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		/*
		 * find the most optimized solution
		 */
		int maxValue = listDataEntries.get(0).getValue();
		List<List<List<Integer>>> selectedSolution = new ArrayList<>();
		
		
		for (Map.Entry<List<List<Integer>>, Integer> entry : listDataEntries) {
			if (entry.getValue() > maxValue) {
				break;
			}
			
			if (Result.convertFormatToList(entry.getKey()).size() <= maxValue) {
				selectedSolution.add(entry.getKey());
			}
		}
		
		
		if (selectedSolution.isEmpty()) {
			selectedSolution.add(listDataEntries.get(0).getKey());
		}
		
		
		
		
		// case 1
		
		return selectedSolution;
	}
}

class PruneSelected1 implements SelectedCallBack {
	@SuppressWarnings("boxing")
	@Override
	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
			List<Integer> taskWeight, List<Integer> taskCount, int days) {
		if (allSolutions == null || allSolutions.isEmpty() || taskWeight == null || taskWeight.isEmpty()
				|| taskCount == null || taskCount.isEmpty() || days <= 0) {
			return Collections.emptyList();
		}
		
		if (allSolutions.size() == 1) {
			return allSolutions;
		}
		
		Map<List<List<Integer>>, Integer> solutionsCacheMap = new HashMap<>();
		
		for (List<List<Integer>> singleSolution : allSolutions) {
			List<Integer> itegrationList = new ArrayList<>();
			
			for (List<Integer> value : singleSolution) {
				itegrationList.addAll(value);
			}
			
			List<Integer> tasksWeightTmp = new ArrayList<>();
			tasksWeightTmp.addAll(taskWeight);
			
			List<Integer> tasksCountTmp = new ArrayList<>();
			tasksCountTmp.addAll(taskCount);
			
			if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, itegrationList)) {
				List<List<List<Integer>>> allOptimizedSolutions = Result.getALLOptimizedSolutions(tasksWeightTmp,
						tasksCountTmp, days);
				
				solutionsCacheMap.put(singleSolution, allOptimizedSolutions.size());
			}
		}
		
		List<Map.Entry<List<List<Integer>>, Integer>> listDataEntries = new ArrayList<>(solutionsCacheMap.entrySet());
		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Integer>>() {
			
			// high -> low
			@Override
			public int compare(Entry<List<List<Integer>>, Integer> o1, Entry<List<List<Integer>>, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		/*
		 * find the most optimized solution
		 */
		int maxValue = listDataEntries.get(0).getValue();
		int sizeNum = Result.convertFormatToList(listDataEntries.get(0).getKey()).size();
		double expected = Result.getExpectedValue(Result.convertFormatToList(listDataEntries.get(0).getKey()));
		List<List<Integer>> selectedList = new ArrayList<>();
		
		for (Map.Entry<List<List<Integer>>, Integer> entry : listDataEntries) {
			if (entry.getValue() < maxValue) {
				break;
			}
			
			if (Result.convertFormatToList(entry.getKey()).size() < sizeNum) {
				selectedList.addAll(entry.getKey());
			}
		}
		
		if (selectedList.isEmpty()) {
			selectedList = listDataEntries.get(0).getKey();
		}
		
		// case 1
		List<List<List<Integer>>> selectedSolution = new ArrayList<>();
		selectedSolution.add(selectedList);
		
		return selectedSolution;
	}
}

class MinExpectationSelected implements SelectedCallBack {

	@Override
	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
			List<Integer> taskWeight, List<Integer> taskCount, int days) {
		if (allSolutions == null || allSolutions.isEmpty()) {
			return null;
		}

		if (allSolutions.size() == 1) {
			return allSolutions;
		}

		int buttom[] = new int[Result.MAX_DAYS];
		double buttomProblem[] = new double[Result.MAX_DAYS];
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

		for (int i = 0; i < buttom.length; ++i) {
			buttomProblem[i] = buttom[i] / (double) countsSum;
		}

		Map<List<List<Integer>>, Double> meanValueCache = new HashMap<>();

		for (List<List<Integer>> singleSolution : allSolutions) {
			List<Integer> itegrationList = new ArrayList<>();
			for (List<Integer> value : singleSolution) {
				itegrationList.addAll(value);
			}

			double singleSum = 0.0;
			for (Integer value : itegrationList) {
				singleSum += value * buttomProblem[value];
			}

			meanValueCache.put(singleSolution, singleSum);
		}

		List<Map.Entry<List<List<Integer>>, Double>> listDataEntries = new ArrayList<>(meanValueCache.entrySet());
		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Double>>() {
			@Override
			public int compare(Entry<List<List<Integer>>, Double> o1, Entry<List<List<Integer>>, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		// case 1
		List<List<List<Integer>>> selected = new ArrayList<>();
		selected.add(listDataEntries.get(0).getKey());

		return selected;
	}
}

public class Result {
	public static final int MAX_DAYS = 101;
	public static SelectedCallBack selectedAlgorithm = new MinExpectationSelected();
	public static double[] probilityRate;
	public static int days = 0;

	@SuppressWarnings("boxing")
	public static void initializeProbilityRate(List<Integer> taskWeight, List<Integer> taskCount) {
		probilityRate = new double[MAX_DAYS];

		int taskSum = 0;
		for (int i = 0; i < taskWeight.size(); ++i) {
			probilityRate[taskWeight.get(i)] = taskCount.get(i);
			taskSum += taskCount.get(i);
		}

		for (int i = 0; i < probilityRate.length; ++i) {
			probilityRate[i] = probilityRate[i] / taskSum;
		}
	}

	public static double getExpectedValue(List<Integer> values) {
		if (values == null || values.isEmpty()) {
			return 0.0;
		}

		double expectedValue = 0.0;
		for (Integer value : values) {
			expectedValue += probilityRate[value];
		}

		return expectedValue;
	}

	public static void setDays(int days) {
		Result.days = days;
	}

	public static void setSelectedAlgorithm(SelectedCallBack selectedAlgorithm) {
		Result.selectedAlgorithm = selectedAlgorithm;
	}
	
	public static int listSum(List<Integer> values){
		if (values == null || values.isEmpty()){
			return 0;
		}
		
		int sum = 0;
		for (Integer value : values){
			sum += value;
		}
		
		return sum;
	}

	/*
	 * optimized DP algorithm
	 */
	public static List<List<List<Integer>>> getSeletedOptimezedSolution(List<Integer> tasksWeight,
			List<Integer> tasksCount, int days) {
		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days < 0) {
			return Collections.emptyList();
		}
		

		List<Integer> expectedValue = new ArrayList<>();
		int i = tasksWeight.size()-1;
		expectedValue.add(tasksWeight.get(i));

		if (tasksWeight.size() == 1 && tasksCount.get(0) == 1){
			List<List<List<Integer>>> allSolutions = new ArrayList<>();
			List<List<Integer>> singleSolutionsList = new ArrayList<>();
			singleSolutionsList.add(new ArrayList<>());
			singleSolutionsList.add(expectedValue);
			allSolutions.add(singleSolutionsList);
			return allSolutions;
		}

		List<Integer> tasksWeightTmp = new ArrayList<>();

		tasksWeightTmp.addAll(tasksWeight);

		List<Integer> tasksCountTmp = new ArrayList<>();
		tasksCountTmp.addAll(tasksCount);

		int remainDays = 0;
		for (Integer singDays : expectedValue) {
			remainDays += singDays;
		}

		remainDays = days - remainDays;
		List<List<List<Integer>>> allOptimizedSolutions = Collections.emptyList();
		if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, expectedValue)) {
			allOptimizedSolutions = Result.getALLOptimizedSolutions(tasksWeightTmp,
					tasksCountTmp, remainDays);
		}
		
		
		for (List<List<Integer>> singleSolution : allOptimizedSolutions) {
			singleSolution.add(expectedValue);
		}

//		SelectedCallBack selectedAlgorithm = new NormalSelected();
		SelectedCallBack selectedAlgorithm = new PruneSelected();
//		SelectedCallBack selectedAlgorithm = new MinExpectationSelected();
		List<List<List<Integer>>> selected = selectedAlgorithm.selectOptimizedAlgorithm(allOptimizedSolutions, tasksWeight,
				tasksCount, days);

		return selected;
	}

	/*
	 * optimized DP algorithm
	 */
	public static List<List<List<Integer>>> getMaxOptimizedSolutionNums(List<Integer> tasksWeight, List<Integer> tasksCount,
			int days) {
		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days < 0) {
			return Collections.emptyList();
		}
		

		List<Integer> expectedValue = new ArrayList<>();
		int i = tasksWeight.size()-1;
		expectedValue.add(tasksWeight.get(i));

		if (tasksWeight.size() == 1 && tasksCount.get(0) == 1){
			List<List<List<Integer>>> allSolutions = new ArrayList<>();
			List<List<Integer>> singleSolutionsList = new ArrayList<>();
			singleSolutionsList.add(new ArrayList<>());
			singleSolutionsList.add(expectedValue);
			allSolutions.add(singleSolutionsList);
			return allSolutions;
		}

		List<Integer> tasksWeightTmp = new ArrayList<>();

		tasksWeightTmp.addAll(tasksWeight);

		List<Integer> tasksCountTmp = new ArrayList<>();
		tasksCountTmp.addAll(tasksCount);

		int remainDays = 0;
		for (Integer singDays : expectedValue) {
			remainDays += singDays;
		}

		remainDays = days - remainDays;
		List<List<List<Integer>>> allOptimizedSolutions = Collections.emptyList();
		if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, expectedValue)) {
			allOptimizedSolutions = Result.getALLOptimizedSolutions(tasksWeightTmp,
					tasksCountTmp, remainDays);
		}
		
		
		for (List<List<Integer>> singleSolution : allOptimizedSolutions) {
			singleSolution.add(expectedValue);
		}
		return allOptimizedSolutions;
	}
	
	/*
	 * optimized DP algorithm
	 */
	public static List<List<List<Integer>>> getALLOptimizedSolutions(List<Integer> taskWeight, List<Integer> taskCount,
			int days) {
		if (taskWeight == null || taskWeight.isEmpty() || taskCount == null || taskCount.isEmpty() || days < 0) {
			return Collections.emptyList();
		}
		
		Dp1 dp[][] = new Dp1[taskWeight.size() + 1][days + 1];
		
		for (int i = 0; i <= taskWeight.size(); ++i) {
			for (int j = 0; j <= days; ++j) {
				dp[i][j] = new Dp1();
			}
		}
		
//		Date date = new Date();
		
		int taskSize = taskWeight.size();
		
		for (int i = 1; i <= taskSize; ++i) {
			for (int j = 1; j <= days; ++j) {
				int taskweight = taskWeight.get(i-1);
				int taskcount = taskCount.get(i-1);
				if (j < taskweight) {
					dp[i][j].value = dp[i - 1][j].value;
					dp[i][j].addLast(0);
				} else {
					int value = 0;
					int k = Math.min(taskcount, j/taskweight);
					for (;k>=0; --k) {
//						for (int k = 0; k * taskweight <= j && k <= taskcount; k++) {
						value = dp[i - 1][j - k * taskweight].value + k * taskweight;
						
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
		
//		Date date1 = new Date();
//		long time = date1.getTime() - date.getTime();
//		System.out.println("time" + time);
		
//		for (int i = 0; i <= taskWeight.size(); ++i) {
//			for (int j = 0; j <= days; ++j) {
//				System.out.print(dp[i][j].value + " ");
//			}
//			System.out.println();
//		}
//
//		for (int i = 0; i <= taskWeight.size(); ++i) {
//			for (int j = 0; j <= days; ++j) {
//				System.out.print(dp[i][j].counts + " ");
//			}
//			System.out.println();
//		}
		
//		getALLOptimizedSolutions1(taskWeight, taskCount,  days);
		
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

	public static void printSelectedResult(List<List<Integer>> seleted) {
		if (seleted == null || seleted.isEmpty()) {
			return;
		}

		for (List<Integer> values : seleted) {
			for (Integer value : values) {
				System.out.print(value + " ");
			}
			System.out.println();
		}
	}

	private static List<List<Integer>> getOptimiedBoxNum(TaskNode rootNode) {
		int minBox;
		List<List<List<Integer>>> allSolutions = TaskNode.getTreeAllPaths(rootNode, new ArrayList<List<Integer>>());

		minBox = allSolutions.get(0).size();
		int len = 0;
		List<List<Integer>> selectedList = allSolutions.get(0);
		for (List<List<Integer>> singleSolution : allSolutions) {
			len = singleSolution.size();
			if (minBox > len) {
				minBox = len;
				selectedList = singleSolution;
			}
		}

		return selectedList;
	}

	public static void initializeSolutionTree(List<Integer> tasksWeight, List<Integer> tasksCount, int days,
			TaskNode parentNode) {
		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days <= 0
				|| parentNode == null) {
			return;
		}
		

		List<List<List<Integer>>> allOptimizedSolutions = getSeletedOptimezedSolution(tasksWeight, tasksCount, days);

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

	public static List<Integer> convertFormatToList(List<List<Integer>> solutions) {
		if (solutions == null || solutions.isEmpty()) {
			return Collections.emptyList();
		}

		List<Integer> result = new ArrayList<>();
		for (List<Integer> value : solutions) {
			result.addAll(value);
		}

		return result;
	}

	@SuppressWarnings("boxing")
	public static int get_people_num(int days, List<Integer> tasks) {
		if (days <= 0 || tasks == null || tasks.isEmpty()) {
			return -1;
		}

		setDays(days);
		int buttom[] = new int[MAX_DAYS];

		for (Integer taskInteger : tasks) {
			if (taskInteger < 0 || taskInteger > days) {
				return -1;
			}
			buttom[taskInteger]++;
		}

		List<Integer> tasksCount = new ArrayList<>();
		List<Integer> tasksWeight = new ArrayList<>();
		int taskAllDays = 0;

		for (int i = 0; i < buttom.length; ++i) {
			if (buttom[i] != 0) {
				tasksCount.add(buttom[i]);
				tasksWeight.add(i);
				taskAllDays += buttom[i] * i;
			}
		}

		initializeProbilityRate(tasksWeight, tasksCount);

		int minBox = 0;
		int theoreticalMim = taskAllDays / days;

		if ((taskAllDays % days) != 0) {
			theoreticalMim += 1;
		}

		TaskNode rootNode = new TaskNode();
		List<List<Integer>> selected = null;

		initializeSolutionTree(tasksWeight, tasksCount, days, rootNode);

		selected = getOptimiedBoxNum(rootNode);
		minBox = selected.size() - 1;

		printSelectedResult(selected);
		return minBox;
	}
}
