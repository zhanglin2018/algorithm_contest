//package com.advantest.algorithm.draft;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.Deque;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.Map.Entry;
//
//import javax.swing.ListModel;
//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
//import javax.xml.bind.DataBindingException;
//
//
//class Package{
//	public int cost;
//	public int count;
//	public Package(int cost, int count) {
//		super();
//		this.cost = cost;
//		this.count = count;
//	}
//}
//
//class Dp1 {
//	public int value;
//	public List<Integer> counts;
//
//	Dp1() {
//		this.value = 0;
//		this.counts = new ArrayList<Integer>();
//	}
//
//	void addLast(int count) {
//		counts.add(count);
//	}
//
//	void removeAll() {
//		if (!counts.isEmpty()) {
//			counts.clear();
//		}
//	}
//
//	@Override
//	public String toString() {
//		return "Dp1 [value=" + value + ", counts=" + counts + "]";
//	}
//}
//
//class TaskNode {
//	public TaskNode parent;
//	public List<TaskNode> childrens;
//	public List<Integer> values;
//	public static List<List<List<Integer>>> paths = new ArrayList<List<List<Integer>>>();
//	public static List<List<Integer>> paths1 = new ArrayList<List<Integer>>();
//
//	public TaskNode() {
//		parent = null;
//		childrens = new ArrayList<TaskNode>();
//		values = new ArrayList<Integer>();
//	}
//
//	@Override
//	public String toString() {
//		return "TaskNode [values=" + values + "]";
//	}
//
//	public static List<List<List<Integer>>> getTreeAllPaths(TaskNode treeNode, List<List<Integer>> path) {
//		if (!paths.isEmpty()) {
//			paths.clear();
//		}
//
//		List<List<List<Integer>>> resultList = new ArrayList<List<List<Integer>>>();
//		getTreeAllPathsImp(treeNode, path);
//
//		for (List<List<Integer>> value : paths) {
//			resultList.add(value);
//		}
//
//		return resultList;
//	}
//
//	private static List<List<List<Integer>>> getTreeAllPathsImp(TaskNode treeNode, List<List<Integer>> path) {
//
//		if (treeNode == null || path == null) {
//			return paths;
//		}
//
//		List<List<Integer>> pathTmp = new ArrayList<List<Integer>>();
//		pathTmp.addAll(path);
//		pathTmp.add(treeNode.values);
//
//		if (treeNode.childrens.isEmpty()) {
//			paths.add(pathTmp);
//			return paths;
//		}
//
//		for (TaskNode node : treeNode.childrens) {
//			getTreeAllPathsImp(node, pathTmp);
//		}
//		return paths;
//	}
//
//	public static List<List<Integer>> getTreeAllPaths1(TaskNode treeNode, List<Integer> path) {
//		if (!paths1.isEmpty()) {
//			paths1.clear();
//		}
//
//		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
//		getTreeAllPathsImp1(treeNode, path);
//
//		for (List<Integer> value : paths1) {
//			resultList.add(value);
//		}
//
//		return resultList;
//	}
//
//	private static List<List<Integer>> getTreeAllPathsImp1(TaskNode treeNode, List<Integer> path) {
//
//		if (treeNode == null || path == null) {
//			return paths1;
//		}
//
//		List<Integer> pathTmp = new ArrayList<Integer>();
//		pathTmp.addAll(path);
//		pathTmp.addAll(treeNode.values);
//
//		if (treeNode.childrens.isEmpty()) {
//			paths1.add(pathTmp);
//			return paths1;
//		}
//
//		for (TaskNode node : treeNode.childrens) {
//			getTreeAllPathsImp1(node, pathTmp);
//		}
//		return paths1;
//	}
//}
//
//class TaskSolutionsManager {
//	public static Map<Integer, List<List<Integer>>> taskSolutionCache = new HashMap<>();
//	public static Set<List<Integer>> taskSolutionSet = new HashSet<>();
//
//	public static int getSpecificSolutionNum(int value) {
//		if (taskSolutionCache.isEmpty()) {
//			return 0;
//		}
//
//		List<List<Integer>> solutions = taskSolutionCache.get(value);
//
//		return solutions.size();
//	}
//
//	@SuppressWarnings("boxing")
//	public static List<List<Integer>> getSpecificSolutions(int value) {
//		if (taskSolutionCache.isEmpty()) {
//			return Collections.emptyList();
//		}
//
//		List<List<Integer>> solutions = new ArrayList<>();
//		solutions.addAll(taskSolutionCache.get(value));
//		return solutions;
//	}
//
//	public static boolean initializeSolution(List<Integer> tasksWeight, List<Integer> tasksCount, int days) {
//		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days <= 0) {
//			return false;
//		}
//
//		taskSolutionSet.clear();
//
//		Result.setSelectedAlgorithm(new NormalSelected());
//
//		List<Integer> expectedValue = new ArrayList<>();
//		for (int i = 0; i < tasksWeight.size(); ++i) {
//			expectedValue.clear();
//			for (int j = 1; j <= tasksCount.get(i); ++j) {
//				expectedValue.add(tasksWeight.get(i));
//
//				List<Integer> tasksWeightTmp = new ArrayList<>();
//
//				tasksWeightTmp.addAll(tasksWeight);
//
//				List<Integer> tasksCountTmp = new ArrayList<>();
//				tasksCountTmp.addAll(tasksCount);
//
//				int remainDays = 0;
//				for (Integer singDays : expectedValue) {
//					remainDays += singDays;
//				}
//
//				remainDays = days - remainDays;
//
//				if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, expectedValue)) {
//					List<List<List<Integer>>> allOptimizedSolutions = Result.getALLOptimizedSolutions(tasksWeightTmp,
//							tasksCountTmp, remainDays);
//
//					for (List<List<Integer>> singleSolution : allOptimizedSolutions) {
//						List<Integer> itegrationList = new ArrayList<>();
//						itegrationList.addAll(expectedValue);
//
//						for (List<Integer> value : singleSolution) {
//							itegrationList.addAll(value);
//						}
//
//						Collections.sort(itegrationList, new Comparator<Integer>() {
//
//							@Override
//							public int compare(Integer o1, Integer o2) {
//								// TODO Auto-generated method stub
//								return o1.compareTo(o2);
//							}
//
//						});
//
//						taskSolutionSet.add(itegrationList);
//
//					}
//				}
//			}
//		}
//
//		System.out.println(taskSolutionSet);
//		return true;
//	}
//
////	public static boolean initializeSolution1(List<Integer> tasksWeight, List<Integer> tasksCount, int days) {
////		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days <= 0) {
////			return false;
////		}
////		
////		taskSolutionSet.clear();
////		Result.setSelectedAlgorithm(new NormalSelected());
////		
////		
////		List<List<Integer>> resultMatrix = new ArrayList<>();
////		
////		for (int i=0; i<tasksWeight.size(); ++i) {
////			for 
////		}
////		
////		resultMatrix = [[]] * max_weight
////
////    for il, l in enumerate(weights):
////        for i in range(max_weight):
////            if resultMatrix[i]:
////                if i + l < max_weight:
////                    vals = deepcopy(resultMatrix[i])
////                    vals_ = []
////                    for val in vals:
////                        val[il] += 1
////                        if val[il] <= nums[il]:
////                            vals_.append(val)
////                    tmp = deepcopy(resultMatrix[i + l])
////                    tmp.extend(vals_)
////                    resultMatrix[i + l] = tmp
////        val = np.zeros(len(weights))
////        for i in range(l, max_weight + 1, l):
////            val[il] += 1
////            if val[il] > nums[il]:
////                break
////            tmp = deepcopy(resultMatrix[i - 1])
////            tmp.append(deepcopy(val))
////            resultMatrix[i - 1] = tmp
////    return resultMatrix[-weights[0]:]
////		 
////		
////		System.out.println(taskSolutionSet);
////		return true;
////	}
//
//	public static boolean initializeSolution2(List<Integer> tasksWeight, List<Integer> tasksCount, int days) {
//		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days <= 0) {
//			return false;
//		}
//
//		taskSolutionSet.clear();
//
//		Result.setSelectedAlgorithm(new NormalSelected());
//
//		List<Integer> expectedValue = new ArrayList<>();
//		for (int i = 0; i < tasksWeight.size(); ++i) {
//			expectedValue.clear();
//			for (int j = 1; j <= tasksCount.get(i); ++j) {
//				expectedValue.add(tasksWeight.get(i));
//
//				List<Integer> tasksWeightTmp = new ArrayList<>();
//				for (int k = i; k < tasksWeight.size(); k++) {
//					tasksWeightTmp.add(tasksWeight.get(k));
//				}
//
//				List<Integer> tasksCountTmp = new ArrayList<>();
//				for (int k = i; k < tasksWeight.size(); k++) {
//					tasksCountTmp.add(tasksCount.get(k));
//				}
//
//				int remainDays = 0;
//				for (Integer singDays : expectedValue) {
//					remainDays += singDays;
//				}
//
//				remainDays = days - remainDays;
//
//				if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, expectedValue)) {
//					List<List<List<Integer>>> allOptimizedSolutions = Result.getSeletedOptimezedSolution(tasksWeightTmp,
//							tasksCountTmp, remainDays);
//
//					for (List<List<Integer>> singleSolution : allOptimizedSolutions) {
//						List<Integer> itegrationList = new ArrayList<>();
//						itegrationList.addAll(expectedValue);
//
//						for (List<Integer> value : singleSolution) {
//							itegrationList.addAll(value);
//						}
//
//						Collections.sort(itegrationList, new Comparator<Integer>() {
//
//							@Override
//							public int compare(Integer o1, Integer o2) {
//								// TODO Auto-generated method stub
//								return o1.compareTo(o2);
//							}
//
//						});
//
//						taskSolutionSet.add(itegrationList);
//
//					}
//				}
//			}
//		}
//
//		System.out.println(taskSolutionSet);
//		return true;
//	}
//}
//
//interface SelectedCallBack {
//	List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions, List<Integer> taskWeight,
//			List<Integer> taskCount, int days);
//
//}
//
//class NormalSelected implements SelectedCallBack {
//
//	@Override
//	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
//			List<Integer> taskWeight, List<Integer> taskCount, int days) {
//		if (allSolutions == null || allSolutions.isEmpty()) {
//			return null;
//		}
//
//		return allSolutions;
//	}
//
//}
//
//class PruneSelected implements SelectedCallBack {
//	@SuppressWarnings("boxing")
//	@Override
//	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
//			List<Integer> taskWeight, List<Integer> taskCount, int days) {
//		if (allSolutions == null || allSolutions.isEmpty() || taskWeight == null || taskWeight.isEmpty()
//				|| taskCount == null || taskCount.isEmpty() || days <= 0) {
//			return Collections.emptyList();
//		}
//
//		if (allSolutions.size() == 1) {
//			return allSolutions;
//		}
//
//		Map<List<List<Integer>>, Integer> solutionsCacheMap = new HashMap<>();
//
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			List<Integer> tasksWeightTmp = new ArrayList<>();
//			tasksWeightTmp.addAll(taskWeight);
//
//			List<Integer> tasksCountTmp = new ArrayList<>();
//			tasksCountTmp.addAll(taskCount);
//
//			if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, itegrationList)) {
//				List<List<List<Integer>>> allOptimizedSolutions = Result.getALLOptimizedSolutions(tasksWeightTmp,
//						tasksCountTmp, days);
//
//				solutionsCacheMap.put(singleSolution, allOptimizedSolutions.size());
//			}
//		}
//
//		List<Map.Entry<List<List<Integer>>, Integer>> listDataEntries = new ArrayList<>(solutionsCacheMap.entrySet());
//		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Integer>>() {
//
//			// high -> low
//			@Override
//			public int compare(Entry<List<List<Integer>>, Integer> o1, Entry<List<List<Integer>>, Integer> o2) {
//				return o2.getValue().compareTo(o1.getValue());
//			}
//		});
//
//		/*
//		 * find the most optimized solution
//		 */
//		int maxValue = listDataEntries.get(0).getValue();
//		int sizeNum = Result.convertFormatToList(listDataEntries.get(0).getKey()).size();
//		double expected = Result.getExpectedValue(Result.convertFormatToList(listDataEntries.get(0).getKey()));
//		List<List<Integer>> selectedList = new ArrayList<>();
//
//		for (Map.Entry<List<List<Integer>>, Integer> entry : listDataEntries) {
//			if (entry.getValue() < maxValue) {
//				break;
//			}
//
//			if (Result.convertFormatToList(entry.getKey()).size() < sizeNum) {
//				selectedList.addAll(entry.getKey());
//			}
//		}
//
//		if (selectedList.isEmpty()) {
//			selectedList = listDataEntries.get(0).getKey();
//		}
//
//		// case 1
//		List<List<List<Integer>>> selectedSolution = new ArrayList<>();
//		selectedSolution.add(selectedList);
//
//		return selectedSolution;
//	}
//}
//
//class PruneSelected1 implements SelectedCallBack {
//	@SuppressWarnings("boxing")
//	@Override
//	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
//			List<Integer> taskWeight, List<Integer> taskCount, int days) {
//		if (allSolutions == null || allSolutions.isEmpty() || taskWeight == null || taskWeight.isEmpty()
//				|| taskCount == null || taskCount.isEmpty() || days <= 0) {
//			return Collections.emptyList();
//		}
//		
//		if (allSolutions.size() == 1) {
//			return allSolutions;
//		}
//		
//		Map<List<List<Integer>>, Integer> solutionsCacheMap = new HashMap<>();
//		
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//			
//			List<Integer> tasksWeightTmp = new ArrayList<>();
//			tasksWeightTmp.addAll(taskWeight);
//			
//			List<Integer> tasksCountTmp = new ArrayList<>();
//			tasksCountTmp.addAll(taskCount);
//			
//			if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, itegrationList)) {
//				List<List<List<Integer>>> allOptimizedSolutions = Result.getALLOptimizedSolutions(tasksWeightTmp,
//						tasksCountTmp, days);
//				
//				solutionsCacheMap.put(singleSolution, allOptimizedSolutions.size());
//			}
//		}
//		
//		List<Map.Entry<List<List<Integer>>, Integer>> listDataEntries = new ArrayList<>(solutionsCacheMap.entrySet());
//		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Integer>>() {
//			
//			// high -> low
//			@Override
//			public int compare(Entry<List<List<Integer>>, Integer> o1, Entry<List<List<Integer>>, Integer> o2) {
//				return o2.getValue().compareTo(o1.getValue());
//			}
//		});
//		
//		/*
//		 * find the most optimized solution
//		 */
//		int maxValue = listDataEntries.get(0).getValue();
//		int sizeNum = Result.convertFormatToList(listDataEntries.get(0).getKey()).size();
//		double expected = Result.getExpectedValue(Result.convertFormatToList(listDataEntries.get(0).getKey()));
//		List<List<Integer>> selectedList = new ArrayList<>();
//		
//		for (Map.Entry<List<List<Integer>>, Integer> entry : listDataEntries) {
//			if (entry.getValue() < maxValue) {
//				break;
//			}
//			
//			if (Result.getExpectedValue(Result.convertFormatToList(entry.getKey())) < expected) {
//				selectedList.addAll(entry.getKey());
//			}
//		}
//		
//		if (selectedList.isEmpty()) {
//			selectedList = listDataEntries.get(0).getKey();
//		}
//		
//		// case 1
//		List<List<List<Integer>>> selectedSolution = new ArrayList<>();
//		selectedSolution.add(selectedList);
//		
//		return selectedSolution;
//	}
//}
//
//class MinExpectationSelected implements SelectedCallBack {
//
//	@Override
//	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
//			List<Integer> taskWeight, List<Integer> taskCount, int days) {
//		if (allSolutions == null || allSolutions.isEmpty()) {
//			return null;
//		}
//
//		if (allSolutions.size() == 1) {
//			return allSolutions;
//		}
//
//		int buttom[] = new int[Result.MAX_DAYS];
//		double buttomProblem[] = new double[Result.MAX_DAYS];
//		int countsSum = 0;
//
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			for (Integer value : itegrationList) {
//				buttom[value]++;
//				countsSum++;
//			}
//
//		}
//
//		for (int i = 0; i < buttom.length; ++i) {
//			buttomProblem[i] = buttom[i] / (double) countsSum;
//		}
//
//		Map<List<List<Integer>>, Double> meanValueCache = new HashMap<>();
//
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			double singleSum = 0.0;
//			for (Integer value : itegrationList) {
//				singleSum += value * buttomProblem[value];
//			}
//
//			meanValueCache.put(singleSolution, singleSum);
//		}
//
//		List<Map.Entry<List<List<Integer>>, Double>> listDataEntries = new ArrayList<>(meanValueCache.entrySet());
//		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Double>>() {
//			@Override
//			public int compare(Entry<List<List<Integer>>, Double> o1, Entry<List<List<Integer>>, Double> o2) {
//				return o1.getValue().compareTo(o2.getValue());
//			}
//		});
//
//		// case 1
//		List<List<List<Integer>>> selected = new ArrayList<>();
//		selected.add(listDataEntries.get(0).getKey());
//
//		return selected;
//	}
//}
//
//class MinProbabilySelected implements SelectedCallBack {
//
//	@Override
//	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
//			List<Integer> taskWeight, List<Integer> taskCount, int days) {
//		if (allSolutions == null || allSolutions.isEmpty()) {
//			return null;
//		}
//
//		if (allSolutions.size() == 1) {
//			return allSolutions;
//		}
//
//		int buttom[] = new int[Result.MAX_DAYS];
//		double buttomProblem[] = new double[Result.MAX_DAYS];
//		int countsSum = 0;
//
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			for (Integer value : itegrationList) {
//				buttom[value]++;
//				countsSum++;
//			}
//
//		}
//
//		for (int i = 0; i < buttom.length; ++i) {
//			buttomProblem[i] = buttom[i] / (double) countsSum;
//		}
//
//		Map<List<List<Integer>>, Double> meanValueCache = new HashMap<>();
//
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			double singleSum = 0.0;
//			for (Integer value : itegrationList) {
//				singleSum += buttomProblem[value];
//			}
//
//			meanValueCache.put(singleSolution, singleSum);
//		}
//
//		List<Map.Entry<List<List<Integer>>, Double>> listDataEntries = new ArrayList<>(meanValueCache.entrySet());
//		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Double>>() {
//			@Override
//			public int compare(Entry<List<List<Integer>>, Double> o1, Entry<List<List<Integer>>, Double> o2) {
//				return o1.getValue().compareTo(o2.getValue());
//			}
//		});
//
//		List<List<List<Integer>>> selected = new ArrayList<>();
//		selected.add(listDataEntries.get(0).getKey());
//
//		return selected;
//	}
//
//}
//
//class RandomSelected implements SelectedCallBack {
//
//	@Override
//	public List<List<List<Integer>>> selectOptimizedAlgorithm(List<List<List<Integer>>> allSolutions,
//			List<Integer> taskWeight, List<Integer> taskCount, int days) {
//		if (allSolutions == null || allSolutions.isEmpty()) {
//			return null;
//		}
//
//		if (allSolutions.size() == 1) {
//			return allSolutions;
//		}
//
//		int daysSum = 0;
//		for (List<Integer> singleSolution : allSolutions.get(0)) {
//			for (Integer value : singleSolution) {
//				daysSum += value;
//			}
//		}
//
//		if (daysSum != Result.days) {
//			return allSolutions;
//		}
//
//		int buttom[] = new int[Result.MAX_DAYS];
//		double buttomProblem[] = new double[Result.MAX_DAYS];
//		int countsSum = 0;
//
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			for (Integer value : itegrationList) {
//				buttom[value]++;
//				countsSum++;
//			}
//
//		}
//
//		for (int i = 0; i < buttom.length; ++i) {
//			buttomProblem[i] = buttom[i] / (double) countsSum;
//		}
//
//		Map<List<List<Integer>>, Double> meanValueCache = new HashMap<>();
//
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			double singleSum = 0.0;
//			for (Integer value : itegrationList) {
////				singleSum += value * buttomProblem[value];
//				singleSum += value * Math.log(buttomProblem[value]) * (-1);
//			}
//
//			meanValueCache.put(singleSolution, singleSum);
//		}
//
//		List<Map.Entry<List<List<Integer>>, Double>> listDataEntries = new ArrayList<>(meanValueCache.entrySet());
//		Collections.sort(listDataEntries, new Comparator<Map.Entry<List<List<Integer>>, Double>>() {
//			@Override
//			public int compare(Entry<List<List<Integer>>, Double> o1, Entry<List<List<Integer>>, Double> o2) {
//				return o1.getValue().compareTo(o2.getValue());
//			}
//		});
//
//		// random
//
//		List<List<List<Integer>>> selected = new ArrayList<>();
//
//		int index = 0;
//
//		if (listDataEntries.size() > 5) {
//			index = (int) (Math.random() * 5);
//		}
//
//		selected.add(listDataEntries.get(index).getKey());
//		return selected;
//	}
//}
//
//public class Result {
//	public static final int MAX_DAYS = 101;
//	public static SelectedCallBack selectedAlgorithm = new MinExpectationSelected();
//	public static double[] probilityRate;
//	public static int days = 0;
//
//	@SuppressWarnings("boxing")
//	public static void initializeProbilityRate(List<Integer> taskWeight, List<Integer> taskCount) {
//		probilityRate = new double[MAX_DAYS];
//
//		int taskSum = 0;
//		for (int i = 0; i < taskWeight.size(); ++i) {
//			probilityRate[taskWeight.get(i)] = taskCount.get(i);
//			taskSum += taskCount.get(i);
//		}
//
//		for (int i = 0; i < probilityRate.length; ++i) {
//			probilityRate[i] = probilityRate[i] / taskSum;
//		}
//	}
//
//	public static double getExpectedValue(List<Integer> values) {
//		if (values == null || values.isEmpty()) {
//			return 0.0;
//		}
//
//		double expectedValue = 0.0;
//		for (Integer value : values) {
//			expectedValue += probilityRate[value];
//		}
//
//		return expectedValue;
//	}
//
//	public static void setDays(int days) {
//		Result.days = days;
//	}
//
//	public static void setSelectedAlgorithm(SelectedCallBack selectedAlgorithm) {
//		Result.selectedAlgorithm = selectedAlgorithm;
//	}
//
//	public static void algorithm(List<Integer> taskWeight, List<Integer> taskCount, int days) {
//		int dp[][] = new int[taskWeight.size() + 1][days + 1];
//		int path[][] = new int[taskWeight.size() + 1][days + 1];
//		int s = 0;
//
//		for (int i = 1; i <= taskWeight.size(); ++i) {
//			for (int j = 1; j <= days; ++j) {
//				if (j - taskWeight.get(i - 1) < 0)
//					dp[i][j] = dp[i - 1][j];
//				else {
//					s = dp[i - 1][j];
//					for (int k = 1; k * taskWeight.get(i - 1) <= j && k <= taskCount.get(i - 1); k++) {
//						s = Math.max(s, dp[i - 1][j - k * taskWeight.get(i - 1)] + k * taskWeight.get(i - 1));
//					}
//					dp[i][j] = s;
//					path[i][j] = 1;
//				}
//			}
//		}
//
//		System.out.println(dp[taskWeight.size()][days]);
//
//		for (int i = 0; i <= taskWeight.size(); ++i) {
//			for (int j = 0; j <= days; ++j) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		System.out.println();
//
//		for (int i = 0; i <= taskWeight.size(); ++i) {
//			for (int j = 0; j <= days; ++j) {
//				System.out.print(path[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		int i = 5, j = 10;
//		List<Integer> taskCountstmp = new ArrayList<>();
//		taskCountstmp.addAll(taskCount);
//
//		while (i > 0 && j > 0) {
//			if (path[i][j] == 1 && taskCountstmp.get(i - 1) >= 0) {
//				System.out.println(i + " ");
//				j -= taskWeight.get(i - 1);
//				int value = taskCountstmp.get(i - 1);
//				value -= 1;
//			} else
//				i--;
//		}
//
//		List<Integer> selected = new ArrayList<>();
//		j = days;
//		i = 0;
//		for (i = taskWeight.size(); i >= 1; i--) {
//			if (dp[i][j] > dp[i - 1][j]) {
//				selected.add(i);
//				j = j - taskWeight.get(i - 1);
//			}
//		}
//
//		System.out.println(selected);
//
//	}
//
//	/*
//	 * optimized DP algorithm
//	 */
//	public static List<List<List<Integer>>> getSeletedOptimezedSolution(List<Integer> tasksWeight,
//			List<Integer> tasksCount, int days) {
//		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days < 0) {
//			return Collections.emptyList();
//		}
//		
//
//		List<Integer> expectedValue = new ArrayList<>();
//		int i = tasksWeight.size()-1;
//		expectedValue.add(tasksWeight.get(i));
//
//		if (tasksWeight.size() == 1 && tasksCount.get(0) == 1){
//			List<List<List<Integer>>> allSolutions = new ArrayList<>();
//			List<List<Integer>> singleSolutionsList = new ArrayList<>();
//			singleSolutionsList.add(new ArrayList<>());
//			singleSolutionsList.add(expectedValue);
//			allSolutions.add(singleSolutionsList);
//			return allSolutions;
//		}
//
//		List<Integer> tasksWeightTmp = new ArrayList<>();
//
//		tasksWeightTmp.addAll(tasksWeight);
//
//		List<Integer> tasksCountTmp = new ArrayList<>();
//		tasksCountTmp.addAll(tasksCount);
//
//		int remainDays = 0;
//		for (Integer singDays : expectedValue) {
//			remainDays += singDays;
//		}
//
//		remainDays = days - remainDays;
//		List<List<List<Integer>>> allOptimizedSolutions = Collections.emptyList();
//		if (Result.updateTaskList(tasksWeightTmp, tasksCountTmp, expectedValue)) {
//			allOptimizedSolutions = Result.getALLOptimizedSolutions(tasksWeightTmp,
//					tasksCountTmp, remainDays);
//		}
//		
//		
//		for (List<List<Integer>> singleSolution : allOptimizedSolutions) {
//			singleSolution.add(expectedValue);
//		}
//
//		SelectedCallBack selectedAlgorithm = new PruneSelected();
//		List<List<List<Integer>>> selected = selectedAlgorithm.selectOptimizedAlgorithm(allOptimizedSolutions, tasksWeight,
//				tasksCount, days);
//
//		return selected;
//	}
//	
//	/*
//	 * optimized DP algorithm
//	 */
//	public static List<List<List<Integer>>> getSeletedOptimezedSolution1(List<Integer> taskWeight,
//			List<Integer> taskCount, int days) {
//		if (taskWeight == null || taskWeight.isEmpty() || taskCount == null || taskCount.isEmpty() || days < 0) {
//			return Collections.emptyList();
//		}
//		
//		List<List<List<Integer>>> allSolutions = getALLOptimizedSolutions(taskWeight, taskCount, days);
//		if (allSolutions.size() > 300){
////			selectedAlgorithm = new MinExpectationSelected();
////		} else if (allSolutions.size() > 100){
//			selectedAlgorithm = new PruneSelected();
////		} else if (allSolutions.size() > 50){
////			selectedAlgorithm = new RandomSelected();
////		} else {
////			selectedAlgorithm = new NormalSelected();
//		}
//		List<List<List<Integer>>> selected = selectedAlgorithm.selectOptimizedAlgorithm(allSolutions, taskWeight,
//				taskCount, days);
//		
//		return selected;
//	}
//
//	/*
//	 * optimized DP algorithm
//	 */
//	public static List<List<List<Integer>>> getALLOptimizedSolutions(List<Integer> taskWeight, List<Integer> taskCount,
//			int days) {
//		if (taskWeight == null || taskWeight.isEmpty() || taskCount == null || taskCount.isEmpty() || days < 0) {
//			return Collections.emptyList();
//		}
//
//		Dp1 dp[][] = new Dp1[taskWeight.size() + 1][days + 1];
//
//		for (int i = 0; i <= taskWeight.size(); ++i) {
//			for (int j = 0; j <= days; ++j) {
//				dp[i][j] = new Dp1();
//			}
//		}
//		
////		Date date = new Date();
//		
//		int taskSize = taskWeight.size();
//
//		for (int i = 1; i <= taskSize; ++i) {
//			for (int j = 1; j <= days; ++j) {
//				int taskweight = taskWeight.get(i-1);
//				int taskcount = taskCount.get(i-1);
//				if (j < taskweight) {
//					dp[i][j].value = dp[i - 1][j].value;
//					dp[i][j].addLast(0);
//				} else {
//					int value = 0;
//
//					for (int k = 0; k * taskweight <= j && k <= taskcount; k++) {
//						value = dp[i - 1][j - k * taskweight].value + k * taskweight;
//
//						if (dp[i][j].value < value) {
//							dp[i][j].value = value;
//							dp[i][j].removeAll();
//							dp[i][j].addLast(k);
//						} else if (dp[i][j].value == value) {
//							dp[i][j].addLast(k);
//						}
//
//					}
//
//				}
//			}
//		}
//		
////		Date date1 = new Date();
////		long time = date1.getTime() - date.getTime();
////		System.out.println("time" + time);
//
////		for (int i = 0; i <= taskWeight.size(); ++i) {
////			for (int j = 0; j <= days; ++j) {
////				System.out.print(dp[i][j].value + " ");
////			}
////			System.out.println();
////		}
////
////		for (int i = 0; i <= taskWeight.size(); ++i) {
////			for (int j = 0; j <= days; ++j) {
////				System.out.print(dp[i][j].counts + " ");
////			}
////			System.out.println();
////		}
//		 
////		getALLOptimizedSolutions1(taskWeight, taskCount,  days);
//
//		TaskNode rootNode = new TaskNode();
//		fun1(dp, taskWeight.size(), days, taskWeight, taskCount, rootNode);
//
//		List<List<List<Integer>>> allSolutions = TaskNode.getTreeAllPaths(rootNode, new ArrayList<List<Integer>>());
//		return allSolutions;
//	}
//
//	/*
//	 * optimized DP algorithm
//	 */
//	public static List<List<List<Integer>>> getALLOptimizedSolutions1(List<Integer> taskWeight,
//			List<Integer> taskCount, int days) {
//		if (taskWeight == null || taskWeight.isEmpty() || taskCount == null || taskCount.isEmpty() || days < 0) {
//			return null;
//		}
//
//		Dp1 dp[][] = new Dp1[taskWeight.size() + 1][days + 1];
//
//		for (int i = 0; i <= taskWeight.size(); ++i) {
//			for (int j = 0; j <= days; ++j) {
//				dp[i][j] = new Dp1();
//			}
//		}
//
//		
//		int q[] = new int[2*days];
//		
////		Date date = new Date();
//		
//		for (int i=1; i<=taskWeight.size(); ++i) {
//			int weight = taskWeight.get(i-1);
//			int count = Math.min(taskCount.get(i-1), days/weight);
//
//			for (int j=0; j<=weight-1; j++) {
//				int l = 1;
//				int r = 0;
//				
//				for (int k=j; k<= days; k+=weight) {
//					while (l <= r && (k - q[l])/weight > count) {
//						++l;
//					}
//					
//					List<Integer> counts = new ArrayList<>();
//					while (l <= r){
//						int value = dp[i-1][k].value - dp[i-1][q[r]].value;
//						int compare = k-q[r];
//						if (value < compare){
//							break; 
//						}
//						if (value == compare){
//							counts.add((k-q[r])/weight);
//						}
//						--r;
//					}
//					
//					q[++r] = k;
//					dp[i][k].value = dp[i-1][q[l]].value + (k - q[l]);
//					dp[i][k].counts.add(r-l);
//					dp[i][k].counts.addAll(counts);
//				}
//			}
//		}
//		
////		Date date1 = new Date();
////		long time = date1.getTime() - date.getTime();
////		System.out.println("time==" + time);
//		
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
//
//		TaskNode rootNode = new TaskNode();
//		fun1(dp, taskWeight.size(), days, taskWeight, taskCount, rootNode);
//
//		List<List<List<Integer>>> allSolutions = TaskNode.getTreeAllPaths(rootNode, new ArrayList<List<Integer>>());
//		return allSolutions;
//	}
//
//	public static void fun1(Dp1[][] valueMatrix, int row, int column, List<Integer> taskWeight, List<Integer> taskCount,
//			TaskNode results) {
//		if (valueMatrix == null || row < 0 || column < 0 || taskWeight == null || taskCount == null
//				|| results == null) {
//			return;
//		}
//
//		if (valueMatrix[row][column].counts.isEmpty()) {
//			return;
//		}
//
//		for (Integer count : valueMatrix[row][column].counts) {
//			if (count == 0) {
////				TaskNode taskNode = new TaskNode();
////				results.childrens.add(taskNode);
////				taskNode.parent = results;
//				fun1(valueMatrix, row - 1, column, taskWeight, taskCount, results);
//
//			} else {
//				TaskNode taskNode = new TaskNode();
//				results.childrens.add(taskNode);
//				taskNode.parent = results;
//
//				int indexCount = count;
//
//				while (indexCount > 0) {
//					taskNode.values.add(taskWeight.get(row - 1));
//					indexCount--;
//				}
//
//				fun1(valueMatrix, row - 1, column - count * taskWeight.get(row - 1), taskWeight, taskCount, taskNode);
//			}
//		}
//	}
//
//	public static void printSelectedResult(List<List<Integer>> seleted) {
//		if (seleted == null || seleted.isEmpty()) {
//			return;
//		}
//
//		for (List<Integer> values : seleted) {
//			for (Integer value : values) {
//				System.out.print(value + " ");
//			}
//			System.out.println();
//		}
//	}
//
//	private static List<List<Integer>> getOptimiedBoxNum(TaskNode rootNode) {
//		int minBox;
//		List<List<List<Integer>>> allSolutions = TaskNode.getTreeAllPaths(rootNode, new ArrayList<List<Integer>>());
//
//		minBox = allSolutions.get(0).size();
//		int len = 0;
//		List<List<Integer>> selectedList = allSolutions.get(0);
//		for (List<List<Integer>> singleSolution : allSolutions) {
//			len = singleSolution.size();
//			if (minBox > len) {
//				minBox = len;
//				selectedList = singleSolution;
//			}
//		}
//
//		return selectedList;
//	}
//
//	public static void initializeSolutionTree(List<Integer> tasksWeight, List<Integer> tasksCount, int days,
//			TaskNode parentNode) {
//		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty() || days <= 0
//				|| parentNode == null) {
//			return;
//		}
//		
//
//		List<List<List<Integer>>> allOptimizedSolutions = getSeletedOptimezedSolution(tasksWeight, tasksCount, days);
//
//		for (List<List<Integer>> singleSolution : allOptimizedSolutions) {
//			List<Integer> itegrationList = new ArrayList<>();
//			for (List<Integer> value : singleSolution) {
//				itegrationList.addAll(value);
//			}
//
//			TaskNode childNode = new TaskNode();
//			childNode.parent = parentNode;
//			parentNode.childrens.add(childNode);
//			childNode.values.addAll(itegrationList);
//
//			List<Integer> tasksWeightTmp = new ArrayList<>();
//			tasksWeightTmp.addAll(tasksWeight);
//			List<Integer> tasksCountTmp = new ArrayList<>();
//			tasksCountTmp.addAll(tasksCount);
//
//			if (updateTaskList(tasksWeightTmp, tasksCountTmp, itegrationList)) {
//				initializeSolutionTree(tasksWeightTmp, tasksCountTmp, days, childNode);
//			}
//		}
//
//	}
//
//	@SuppressWarnings("boxing")
//	public static boolean updateTaskList(List<Integer> tasksWeight, List<Integer> tasksCount,
//			List<Integer> expectedValue) {
//		if (tasksWeight == null || tasksWeight.isEmpty() || tasksCount == null || tasksCount.isEmpty()
//				|| expectedValue == null || expectedValue.isEmpty()) {
//			return false;
//		}
//
//		for (Integer value : expectedValue) {
//			int index = tasksWeight.indexOf(value);
//			if (index == -1) {
//				return false;
//			}
//			tasksCount.set(index, tasksCount.get(index) - 1);
//		}
//
//		for (int i = tasksCount.size() - 1; i >= 0; --i) {
//			if (tasksCount.get(i) == 0) {
//				tasksWeight.remove(i);
//				tasksCount.remove(i);
//			}
//		}
//
//		return true;
//	}
//
//	public static List<Integer> convertFormatToList(List<List<Integer>> solutions) {
//		if (solutions == null || solutions.isEmpty()) {
//			return Collections.emptyList();
//		}
//
//		List<Integer> result = new ArrayList<>();
//		for (List<Integer> value : solutions) {
//			result.addAll(value);
//		}
//
//		return result;
//	}
//
//	@SuppressWarnings("boxing")
//	public static int get_people_num(int days, List<Integer> tasks) {
//		if (days <= 0 || tasks == null || tasks.isEmpty()) {
//			return -1;
//		}
//
//		setDays(days);
//		int buttom[] = new int[MAX_DAYS];
//
//		for (Integer taskInteger : tasks) {
//			if (taskInteger < 0 || taskInteger > days) {
//				return -1;
//			}
//			buttom[taskInteger]++;
//		}
//
//		List<Integer> tasksCount = new ArrayList<>();
//		List<Integer> tasksWeight = new ArrayList<>();
//		int taskAllDays = 0;
//
//		for (int i = 0; i < buttom.length; ++i) {
//			if (buttom[i] != 0) {
//				tasksCount.add(buttom[i]);
//				tasksWeight.add(i);
//				taskAllDays += buttom[i] * i;
//			}
//		}
//
//		/*
//		 * initialize taskSolution Cache
//		 */
////		TaskSolutionsManager.initializeSolution(tasksWeight, tasksCount, days);
////		TaskSolutionsManager.initializeSolution2(tasksWeight, tasksCount, days);
//
//		
//		initializeProbilityRate(tasksWeight, tasksCount);
//
//		int minBox = 0;
//		int theoreticalMim = taskAllDays / days;
//
//		if ((taskAllDays % days) != 0) {
//			theoreticalMim += 1;
//		}
//
//		TaskNode rootNode = new TaskNode();
//		List<List<Integer>> selected = null;
//
////		setSelectedAlgorithm(new PruneSelected());
////		setSelectedAlgorithm(new MinExpectationSelected());
//		initializeSolutionTree(tasksWeight, tasksCount, days, rootNode);
//
//		selected = getOptimiedBoxNum(rootNode);
//		minBox = selected.size() - 1;
//
//		printSelectedResult(selected);
//		return minBox;
//	}
//
//	@SuppressWarnings("boxing")
//	public static int get_people_num1(int days, List<Integer> tasks) {
//		if (days <= 0 || tasks == null || tasks.isEmpty()) {
//			return -1;
//		}
//
//		setDays(days);
//		int buttom[] = new int[MAX_DAYS];
//
//		for (Integer taskInteger : tasks) {
//			if (taskInteger < 0 || taskInteger > days) {
//				return -1;
//			}
//			buttom[taskInteger]++;
//		}
//
//		List<Integer> tasksCount = new ArrayList<>();
//		List<Integer> tasksWeight = new ArrayList<>();
//		int taskAllDays = 0;
//
//		for (int i = 0; i < buttom.length; ++i) {
//			if (buttom[i] != 0) {
//				tasksCount.add(buttom[i]);
//				tasksWeight.add(i);
//				taskAllDays += buttom[i] * i;
//			}
//		}
//
//		/*
//		 * initialize taskSolution Cache
//		 */
//		TaskSolutionsManager.initializeSolution(tasksWeight, tasksCount, days);
////		TaskSolutionsManager.initializeSolution2(tasksWeight, tasksCount, days);
//
//		int minBox = 0;
//		int theoreticalMim = taskAllDays / days;
//
//		if ((taskAllDays % days) != 0) {
//			theoreticalMim += 1;
//		}
//
//		TaskNode rootNode = new TaskNode();
//		List<List<Integer>> selected = null;
//
//		List<SelectedCallBack> allSelectedCallBacks = new ArrayList<>();
//		allSelectedCallBacks.add(new MinProbabilySelected());
//
//		for (int i = 0; i < 10; i++) {
//			allSelectedCallBacks.add(new RandomSelected());
//		}
//
////		setSelectedAlgorithm(new MinExpectationSelected());
//		setSelectedAlgorithm(new PruneSelected());
//		initializeSolutionTree(tasksWeight, tasksCount, days, rootNode);
//
//		selected = getOptimiedBoxNum(rootNode);
//		minBox = selected.size() - 1;
//
//		if (minBox == theoreticalMim) {
//			printSelectedResult(selected);
//			return minBox;
//		}
//
//		for (int i = 0; i < allSelectedCallBacks.size(); i++) {
//			setSelectedAlgorithm(allSelectedCallBacks.get(i));
//			initializeSolutionTree(tasksWeight, tasksCount, days, rootNode);
//			List<List<Integer>> tmp = getOptimiedBoxNum(rootNode);
//			if (minBox > (selected.size() - 1)) {
//				selected = tmp;
//				minBox = selected.size() - 1;
//
//				if (minBox == theoreticalMim) {
//					printSelectedResult(selected);
//					return minBox;
//				}
//			}
//		}
//
//		printSelectedResult(selected);
//		return minBox;
//	}
//
//}
