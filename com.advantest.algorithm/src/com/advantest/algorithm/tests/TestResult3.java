package com.advantest.algorithm.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.adavantest.algorithm.Result;

public class TestResult3 {
	private List<Integer> generateTestDate(String fileName, List<Integer> info) throws NumberFormatException, IOException{
//		String filePath = "resources/test";
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(fileName));
        
        info.add(Integer.parseInt(bufferedReader2.readLine().trim()));
        info.add(Integer.parseInt(bufferedReader2.readLine().trim()));
        
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
	
	@Test
	public void testAlgorithm_testNum_case3() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 59;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case3.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case3:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case4() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 53;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case4.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case4:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case5() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 60;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case5.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case5:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case6() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 46;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case6.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case6:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case7() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 49;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case7.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case7:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case8() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 51;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case8.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case8:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case9() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 56;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case9.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case9:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case10() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 57;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case10.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case10:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case11() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 56;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case11.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case11:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case12() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 58;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case12.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case12:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case13() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 49;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case13.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case13:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case14() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 55;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case14.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case14:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case15() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 51;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case15.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case15:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case16() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 48;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case16.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case16:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case17() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 55;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case17.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case17:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case18() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 57;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case18.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case18:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case19() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 54;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case19.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case19:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case20() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 48;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case20.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case20:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case21() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 49;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case21.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case21:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
	@Test
	public void testAlgorithm_testNum_case22() throws NumberFormatException, IOException {
		int days = 100;
		int expected = 54;
		List<Integer> info = new ArrayList<>();
		List<Integer> tasks =  generateTestDate("resources1/case22.txt", info);
		
		System.out.println("size= " + tasks.size());
		System.out.println("testAlgorithm_testNum_case22:    " + tasks + "     days: " + days + "     expected: " + expected);
		Assert.assertEquals(expected, Result.get_people_num(info.get(0), tasks));
		System.out.println();	
	}
}
