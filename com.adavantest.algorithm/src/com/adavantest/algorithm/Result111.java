package com.adavantest.algorithm;

public class Result111 {
	//给定一个和，求数组中和为这个值的一组数，O(n)
	static boolean findTwoNumOfSum(int a[],int n,int sum, int num1, int num2)
	{
		int i=0,j=n-1;
		while(i<j)
		{
			if(a[i]+a[j]>sum)
				j--;
			if(a[i]+a[j]<sum)
				i++;
			if(a[i]+a[j]==sum)
			{
				num1=a[i];
				num2=a[j];
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		int num1 = 0,num2 = 0;
		int a[]={2,2,2,2,3,3,6};
		int n=7;
		
		findTwoNumOfSum(a,n,4,num1,num2);
		System.out.println(num1 + " " + num2);
	}
}
