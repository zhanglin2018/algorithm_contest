package com.advantest.algorithm.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntToDoubleFunction;

import org.junit.Assert;
import org.junit.Assert.*;
import org.omg.CORBA.portable.ValueBase;
import org.junit.Test;

import com.adavantest.algorithm.Result;

public class TestResult2 {


	@Test
	public void testAlgorithm3() {
		int days = 10;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 6, 3, 3, 2, 2, 2, 2 });
		List<Integer> tasks1 = Arrays.asList(new Integer[] { 3, 3, 3, 2, 2, 2, 2 });
		
		System.out.println("testAlgorithm3:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
	}
	
	@Test
	public void testAlgorithm1() {
		
		List<Integer> tasks11 = Arrays.asList(new Integer[] { 6, 3, 3, 2, 2, 2, 2 });
		List<Integer> tasks111 = Arrays.asList(new Integer[] { 3, 6, 3, 2, 2, 2, 2 });
		System.out.println(tasks11.equals(tasks111));
		
		Integer arr[] = new Integer[10];
		arr[0] = 0;
		arr[0] = 0;
		arr[0] = 3;
		arr[0] = 4;
		arr[0] = 3;
		Integer arr1[] = new Integer[10];
		arr1[0] = 0;
		arr1[0] = 0;
		arr1[0] = 3;
		arr1[0] = 4;
		arr1[0] = 3;
		
		boolean isequal = true;
		for (int i=0;i<arr.length;i++){
			if (arr1[i] != arr[i]){
				isequal = false;
			}
		}
		System.out.println(isequal);
		
		System.out.println();	
		
		List<Integer> tasks1111 = Arrays.asList(new Integer[] {0, 0, 0, 2, 0, 0, 4, 2, 2, 0, 4, 2, 0, 2, 0, 4, 0, 0, 2, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 4, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0
 });
		List<Integer> tasks11111 = Arrays.asList(new Integer[]{0, 0, 0, 2, 0, 0, 4, 2, 2, 0, 4, 2, 0, 2, 0, 4, 0, 0, 2, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 4, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 });
		System.out.println("equal: " + tasks1111.equals(tasks11111));
	}

	@Test
	public void testAlgorithm4() {
		int days = 15;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 4, 5, 6, 7, 8 });
		
		System.out.println("testAlgorithm4:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm5() {
		int days = 100;
		int expected = 1;
		List<Integer> tasks = Arrays.asList(new Integer[] { 10, 20, 30, 40 });
		
		System.out.println("testAlgorithm5:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm6() {
		int days = 10;
		int expected = -1;
		List<Integer> tasks = Arrays.asList(new Integer[] { 7, 8, 9, 10, 11 });
		
		System.out.println("testAlgorithm6:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm7() {
		int days = 15;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 10, 6, 4, 3, 2 });
		
		System.out.println("testAlgorithm7:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm8() {
		int days = 100;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 6, 12, 15, 40, 43, 82 });
		
		System.out.println("testAlgorithm8:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm9() {
		int days = 10;
		int expected = 3;
		List<Integer> tasks = Arrays.asList(new Integer[] { 1, 2, 2, 3, 5, 6, 8 });
		
		System.out.println("testAlgorithm9:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm10() {
		int days = 28;
		int expected = 3;
		List<Integer> tasks = Arrays.asList(new Integer[] { 4, 4, 6, 7, 8, 9, 9, 9, 12, 15 });
		
		System.out.println("testAlgorithm10:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm11() {
		int days = 100;
		int expected = 10;
		List<Integer> tasks = Arrays.asList(new Integer[] { 51, 52, 53, 54, 55, 51, 52, 53, 54, 55 });
		
		System.out.println("testAlgorithm11:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	}

	@Test
	public void testAlgorithm12() {
		int days = 10;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 3, 3, 3, 3, 4, 4 });
		
		System.out.println("testAlgorithm12:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	}

	@Test
	public void testAlgorithm13() {
		int days = 96;
		int expected = 9;
		List<Integer> tasks = Arrays.asList(new Integer[] { 49, 49, 49, 49, 49, 49, 26, 26, 26, 26, 26, 26, 25, 25, 25,
				25, 25, 25, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22 });
		
		System.out.println("testAlgorithm13:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	}

	@Test 
	public void testAlgorithm14() {
		int days = 28;
		int expected = 3;
		Date startDate = new Date();
		List<Integer> tasks = Arrays.asList(new Integer[] { 4, 4, 6, 7, 8, 9, 9, 9, 12, 15 });
		
		System.out.println("testAlgorithm14:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		Date endDate = new Date();
		System.out.println(endDate.getTime() - startDate.getTime());	
		}
	
	@Test
	public void testAlgorithm15() {
		int days = 100;
		int expected = 31;
		
		List<Integer> tasks = Arrays.asList(new Integer[] { 100, 99, 97,97,97,93,93,92,92,88,83,83,79,
				76,76,75,72,71,70,69,67,66,63,62,62,61,61,51,50,44,44,43,43,40,39,37,37,30,23,
				20,19,18,17,15,14,13,13,12,8,8, });
		System.out.println("testAlgorithm15:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		Date endDate = new Date();
	}
	
	@Test
	public void testAlgorithm16() {
	    int days = 100;
	    int expected = 20;
	    List<Integer> tasks = Arrays.asList(new Integer[] { 92,89,87,84,82, 82, 81,75, 73,71,67, 67,63,59,
		    57,56,52,49,48,47,46,41, 39, 38, 36, 35, 34, 34, 30, 29, 26, 21, 20, 19, 18, 15, 15, 13, 11,
		    10,10,10,9,8,8,7,6,6,6,3 });
	    System.out.println("testAlgorithm16:    " + tasks + "     days: " + days + "     expected: " + expected);
	    Assert.assertEquals(expected, Result.get_people_num(days, tasks));
	    System.out.println();	
	}
	
	@Test
	public void testAlgorithm17() {
		int days = 100;
		int expected = 40;
		List<Integer> tasks = Arrays.asList(new Integer[] { 92,89,87,84,82, 82, 81,75, 73,71,67, 67,63,59,
				57,56,52,49,48,47,46,41, 39, 38, 36, 35, 34, 34, 30, 29, 26, 21, 20, 19, 18, 15, 15, 13, 11,
				10,10,10,9,8,8,7,6,6,6,3, 92,89,87,84,82, 82, 81,75, 73,71,67, 67,63,59,
				57,56,52,49,48,47,46,41, 39, 38, 36, 35, 34, 34, 30, 29, 26, 21, 20, 19, 18, 15, 15, 13, 11,
				10,10,10,9,8,8,7,6,6,6,3 });
		System.out.println("testAlgorithm17:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm18() {
		int days = 10;
		int expected = 1;
		List<Integer> tasks = Arrays.asList(new Integer[] { 9 });
		System.out.println("testAlgorithm18:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19() {
		int days = 100;
		int expected = 37;
		List<Integer> tasks = Arrays.asList(new Integer[] { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 7, 7, 7, 8, 8, 8, 8, 8, 10, 11, 12, 12, 13, 15, 15, 15, 16, 16, 16, 18, 19, 19, 20, 21, 22, 23, 27, 30, 30, 32, 32, 35, 35, 36, 38, 38, 39, 39, 39, 39, 40, 42, 44, 45, 46, 48, 50, 50, 54, 56, 56, 61, 61, 61, 62, 63, 64, 68, 69, 72, 81, 82, 83, 83, 83, 84, 85, 86, 87, 88, 88, 89, 92, 93, 94, 96, 97, 98, 99});
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19_2() {
		int days = 100;
		int expected = 35;
		List<Integer> tasks = Arrays.asList(new Integer[] { 1, 1, 1, 1, 1, 1, 2, 3, 3, 
				3, 3, 3, 3, 4, 4, 4, 4, 6, 6, 6, 7, 8, 
				8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 14, 
				15, 16, 17, 17, 18, 18, 20, 21, 22, 22, 
				24, 26, 26, 28, 28, 29, 30, 30, 31, 31, 
				32, 32, 32, 33, 40, 40, 41, 43, 43, 43, 
				44, 44, 45, 45, 45, 46, 46, 46, 49, 54, 
				56, 56, 57, 57, 57, 58, 59, 62, 64, 65, 
				66, 69, 70, 72, 74, 78, 83, 84, 86, 86, 
				86, 87, 87, 91, 92, 93, 99});
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19_2:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19_3() {
		int days = 100;
		int expected = 35;
		List<Integer> tasks = Arrays.asList(new Integer[] {1, 1, 2, 2, 2, 2, 3,
				3, 3, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 8
				, 8, 8, 8, 8, 9, 9, 9, 10, 10, 11, 11,
				11, 12, 14, 15, 16, 16, 17, 19, 22, 23,
				24, 24, 24, 25, 26, 27, 28, 29, 29, 30, 
				30, 31, 34, 34, 35, 35, 35, 37, 38, 38, 
				41, 43, 47, 47, 49, 50, 50, 51, 53, 53, 
				56, 57, 59, 63, 63, 65, 66, 66, 68, 72,
				72, 72, 73, 73, 77, 81, 84, 84, 85, 85, 86, 86, 90, 92, 92, 96, 99});
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19_3:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19_4() {
		int days = 100;
		int expected = 35;
		List<Integer> tasks = Arrays.asList(new Integer[] {1, 1, 2, 2, 2, 2, 
				2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 
				6, 6, 6, 6, 7, 9, 11, 11, 12, 12, 13, 14, 
				14, 16, 18, 18, 19, 21, 21, 22, 22, 23, 24,
				24, 25, 26, 26, 27, 28, 29, 30, 30, 36, 36,
				38, 40, 40, 40, 43, 44, 44, 45, 45, 46, 46, 
				48, 48, 49, 51, 51, 51, 54, 54, 54, 55, 56, 
				57, 59, 60, 60, 60, 64, 67, 68, 68, 70, 72, 
				77, 78, 79, 80, 81, 87, 88, 91, 93, 94, 97, 98});
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19_4:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19_5() {
		int days = 100;
		int expected = 35;
		List<Integer> tasks = Arrays.asList(new Integer[] {1, 1, 1, 1, 
				1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3,
				3, 3, 4, 4, 4, 5, 5, 5, 6, 7, 7, 7, 
				7, 7, 8, 9, 10, 11, 12, 12, 12, 13,
				13, 14, 14, 15, 17, 18, 18, 21, 21,
				23, 25, 25, 26, 26, 28, 28, 29, 30,
				31, 31, 33, 33, 35, 36, 39, 42, 43,
				44, 52, 57, 63, 64, 67, 67, 69, 70,
				71, 72, 74, 75, 75, 79, 82, 82, 83,
				84, 85, 86, 86, 87, 88, 88, 90, 90, 92, 93, 95, 96, 98, 98});
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19_5:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19_6() {
		int days = 100;
		int expected = 35;
		List<Integer> tasks = Arrays.asList(new Integer[] {1, 1, 1, 1, 1,
				2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5,
				5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9,
				9, 9, 9, 10, 11, 12, 13, 13, 15,
				15, 15, 16, 16, 17, 19, 19, 20,
				22, 22, 27, 28, 29, 29, 29, 29,
				30, 33, 35, 38, 38, 41, 42, 42,
				43, 43, 46, 51, 52, 57, 57, 58, 
				58, 63, 65, 69, 71, 71, 71, 73, 
				76, 77, 78, 81, 84, 84, 84, 85,
				87, 91, 91, 91, 92, 92, 94, 95, 97, 98, 99});
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19_6:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19_7() {
		int days = 100;
		int expected = 45;
		List<Integer> tasks = Arrays.asList(new Integer[] {20,20,20,20,21,22,22,22,22,23,23,23,23,23,25,26,26,26,
				27,29,29,29,29,30,30,30,31,32,32,32,33,33,33,34,35,35,35,36,36,37,37,37,38,38,39,40,40,42,42,42,
				44,44,45,47,48,49,50,50,50,51,52,52,53,53,54,54,55,55,55,56,57,58,58,58,58,59,60,60,60,61,61,62,62,
				62,62,63,63,64,65,65,65,66,66,66,66,67,67,68,69,69});
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19_7:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	@Test
	public void testAlgorithm19_8() {
		int days = 100;
		int expected = 13;
		List<Integer> tasks = Arrays.asList(new Integer[] {23, 22, 55, 
				23, 22, 55, 
				23, 23, 54, 
				23, 22, 54, 
				47, 53, 
				44, 53, 
				48, 52, 
				29, 52, 
				49, 51, 
				50, 50, 
				29, 50, 
				29, 27, 29, 
				26, 26, 26, 
				25, 
});
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19_8:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	
	@Test
	public void testAlgorithm19_1() {
		int days = 10;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 1,2,2,3,6,6});
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm19:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(days, tasks));
		System.out.println();	
	}
	
	
	@Test
	public void testAlgorithm20() {
		List<Integer> datas = generateAllData();
		Collections.sort(datas);
		System.out.println(100);
		System.out.println(datas.size());
		for (Integer value : datas){
			System.out.println(value);
		}
	}
	
	
	
	private List<Integer> generateAllData(){
//		15*2 + 10*3 + 10*4

		List<Integer> values = new ArrayList<>();
		for (int i=0; i< 15; i++){
			values.addAll(generateTwo());
		}
		for (int i=0; i< 10; i++){
			values.addAll(generateThree());
		}
		for (int i=0; i< 10; i++){
			values.addAll(generateFour());
		}
		
		return values;
	}
	
	
	private List<Integer> generateTwo(){
		int A1 = 100;
		int B1 = (int) (A1 * Math.random() + 1);
		int C1 =  A1-B1;
		if (C1 <= 0){
			C1++;
			B1--;
		}
		
		List<Integer> value = new ArrayList<>();
		value.add(B1);
		value.add(C1);
		return value;
	}
	
	private List<Integer> generateThree(){
		int A1 = 100;
		int B1 = (int) (A1 * Math.random() + 1);
		int C1 = (int) ((A1-B1) * Math.random() + 1);
		int D1 = A1-B1-C1;
		if (D1 <= 0){
			D1++;
			B1--;
		}
		List<Integer> value = new ArrayList<>();
		value.add(B1);
		value.add(C1);
		value.add(D1);
		return value;
	}
	
	private List<Integer> generateFour(){
		int A1 = 100;
		int B1 = (int) (A1 * Math.random() + 1);
		int C1 = (int) ((A1-B1) * Math.random() + 1);
		int D1 = (int) ((A1-B1-C1) * Math.random() + 1);
		int F1 = A1-B1-C1-D1;
		if (F1 <=0 ){
			F1++;
			B1--;
		}
		List<Integer> value = new ArrayList<>();
		value.add(B1);
		value.add(C1);
		value.add(D1);
		value.add(F1);
		return value;
	}
}
