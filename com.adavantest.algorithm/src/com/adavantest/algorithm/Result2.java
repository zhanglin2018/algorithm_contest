package com.adavantest.algorithm;

public class Result2{
	
	public static int Knapsack(int v[],int w[],int c,int n,int m[][])
	{
		int jMax=Min(w[n]-1,c);//自底向上，若最后一个物体的重量小于背包的总容量，取最后一个物体的重量为界限
		//小于w[n]都放不不了
		for(int j=0;j<=jMax;j++)m[n][j]=0;//背包容量小于最后一个物品的重量，不能放入该物品
		for(int j=w[n];j<=c;j++) m[n][j]=v[n];//大于w[n]能放入
		int i;
		for(i=n-1;i>0;i--){//从n-1到1
			jMax=Min(w[i]-1,c);
			for(int j=0;j<=jMax;j++){//背包容量j小于物体w[i]，则不能放入
				m[i][j]=m[i+1][j];
			}
			for(int j=w[i];j<=c;j++) {
				m[i][j]=Max(m[i+1][j],m[i+1][j-w[i]]+v[i]);//大于则尝试放入，与不放入相比，区总价值大的
			}
		}
		m[0][c]=m[1][c];//此时m[1][c],m[2][c],m[3][c]......m[n-1][c],m[n][c]的最优值已算出来
		if(c>=w[0]){
			m[0][c]=Max(m[i+1][c],m[i+1][c-w[1]]+v[0]);//此时i等于0
		}
		return m[0][c];
	}
	
	//输出选取物体的下标，从0开始
	public static void Tracback(int v[],int w[],int c,int n,int m[][]){
		int x[] = new int[5];
		for(int i=0;i<n;i++){
			if(m[i][c]==m[i+1][c]){//相等则该物体不屈
				x[i]=0;
			}else{
				c=c-w[i];//取该物体后背包容量建w[i]
				x[i]=1;
			}	
		}
		if(m[n][c]>w[n]) {//只剩最后一个一物体，若背包容量能装下，则一定取
			x[n]=1;
		}else{
			x[n]=0;
		}
		for(int i=0;i<x.length;i++){
			System.out.println(x[i]);
		}
	}
	
	public static	int Max(int a,int b){
		if(a>=b){
			return a;
		}else{
			return b;
		}
	}
	
	public static 	int Min(int a,int b){
		if(a<b){
			return a;
		}else{
			return b;
		}
	}
	
	public static void main(String[] args) {
	    	//背包容量
	    	   int c=10;
	    	   //物体个数
	    	    int n=4;
	    	    //物品质量
	    	    int w[]={2,2,6,5,4};
	    	    //物品价值
	    	    int v[]={6,3,5,4,6};
	    		  int m[][] =new int[200][200];
	    	//    int m[][] ={};
	    	System.out.println(Result2.Knapsack(v,w,c,n,m));
	    	Result2.Tracback(v,w,c,n,m);
			
	}
	
}
