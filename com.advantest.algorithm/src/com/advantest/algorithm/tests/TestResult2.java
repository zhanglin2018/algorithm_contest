package com.advantest.algorithm.tests;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import com.adavantest.algorithm.Algorithm;

public class TestResult2 {


	@Test
	public void testAlgorithm3() {
		int days = 10;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 6, 3, 3, 2, 2, 2, 2 });
		
		System.out.println("testAlgorithm3:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm4() {
		int days = 15;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 4, 5, 6, 7, 8 });
		
		System.out.println("testAlgorithm4:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm5() {
		int days = 100;
		int expected = 1;
		List<Integer> tasks = Arrays.asList(new Integer[] { 10, 20, 30, 40 });
		
		System.out.println("testAlgorithm5:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm6() {
		int days = 10;
		int expected = -1;
		List<Integer> tasks = Arrays.asList(new Integer[] { 7, 8, 9, 10, 11 });
		
		System.out.println("testAlgorithm6:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm7() {
		int days = 15;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 10, 6, 4, 3, 2 });
		
		System.out.println("testAlgorithm7:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm8() {
		int days = 100;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 6, 12, 15, 40, 43, 82 });
		
		System.out.println("testAlgorithm8:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm9() {
		int days = 10;
		int expected = 3;
		List<Integer> tasks = Arrays.asList(new Integer[] { 1, 2, 2, 3, 5, 6, 8 });
		
		System.out.println("testAlgorithm9:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm10() {
		int days = 28;
		int expected = 3;
		List<Integer> tasks = Arrays.asList(new Integer[] { 4, 4, 6, 7, 8, 9, 9, 9, 12, 15 });
		
		System.out.println("testAlgorithm10:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();
	}

	@Test
	public void testAlgorithm11() {
		int days = 100;
		int expected = 10;
		List<Integer> tasks = Arrays.asList(new Integer[] { 51, 52, 53, 54, 55, 51, 52, 53, 54, 55 });
		
		System.out.println("testAlgorithm11:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();	}

	@Test
	public void testAlgorithm12() {
		int days = 10;
		int expected = 2;
		List<Integer> tasks = Arrays.asList(new Integer[] { 3, 3, 3, 3, 4, 4 });
		
		System.out.println("testAlgorithm12:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();	}

	@Test
	public void testAlgorithm13() {
		int days = 96;
		int expected = 9;
		List<Integer> tasks = Arrays.asList(new Integer[] { 49, 49, 49, 49, 49, 49, 26, 26, 26, 26, 26, 26, 25, 25, 25,
				25, 25, 25, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22 });
		
		System.out.println("testAlgorithm13:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		System.out.println();	}

	@Test 
	public void testAlgorithm14() {
		int days = 28;
		int expected = 3;
		Date startDate = new Date();
		List<Integer> tasks = Arrays.asList(new Integer[] { 4, 4, 6, 7, 8, 9, 9, 9, 12, 15 });
		
		System.out.println("testAlgorithm14:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		Date endDate = new Date();
		System.out.println(endDate.getTime() - startDate.getTime());	
		}
	
	@Test
	public void testAlgorithm15() {
		int days = 100;
		int expected = 31;
		Date startDate = new Date();
		
		List<Integer> tasks = Arrays.asList(new Integer[] { 100, 99, 97,97,97,93,93,92,92,88,83,83,79,
				76,76,75,72,71,70,69,67,66,63,62,62,61,61,51,50,44,44,43,43,40,39,37,37,30,23,
				20,19,18,17,15,14,13,13,12,8,8 });
		System.out.println("testAlgorithm15:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
		Date endDate = new Date();
		System.out.println(endDate.getTime() - startDate.getTime());	
	}
	
	@Test
	public void testAlgorithm16() {
	    int days = 100;
	    int expected = 20;
	    List<Integer> tasks = Arrays.asList(new Integer[] { 92,89,87,84,82, 82, 81,75, 73,71,67, 67,63,59,
		    57,56,52,49,48,47,46,41, 39, 38, 36, 35, 34, 34, 30, 29, 26, 21, 20, 19, 18, 15, 15, 13, 11,
		    10,10,10,9,8,8,7,6,6,6,3 });
	    System.out.println("testAlgorithm16:    " + tasks + "     days: " + days + "     expected: " + expected);
	    Assert.assertEquals(expected, Algorithm.get_people_num(days, tasks));
	    System.out.println();	
	}
}
