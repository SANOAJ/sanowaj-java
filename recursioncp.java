import java.util.Scanner;

import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
public class Recursion 
{
	
	public static int[] takeInput()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Size ");		
		int n = sc.nextInt();		
		int arr[] = new int[n];
		
		System.out.print("Enter Elements ");
		for(int i=0; i<n; i++)
		{
				arr[i] = sc.nextInt();
			
		}
			
		return arr;
	}
	
	public static int loot(int arr[])
	{
		int n = arr.length;
						
		if(arr.length == 0)
		{
			return 0;
		}
		
		int val1 = arr[0];	
		if(arr.length ==1)
		{
			return val1;
		}
		
		int val2 = Math.max(arr[0], arr[1]);
		if(n == 2)
		{
			return val2;
		}
		
		int max_value =0;
		for(int i=2; i<n; i++)
		{
			max_value = Math.max(arr[i]+val1, val2);
			val1 = val2;
			val2 = max_value;		
		}		
		return max_value;	
	}
	
	public static int minStep(int n, int dp[])
	{
		if(n == 1)
		{
			return 0;
		}
		
		int ans1;
		int ans2 = Integer.MAX_VALUE;
		int ans3 = Integer.MAX_VALUE;
		
		if(dp[n-1] == -1)
		{
			ans1 = minStep(n-1, dp);
			dp[n-1] = ans1;
		}
		else
		{
			ans1 = dp[n-1];
		}
		
		if(n%2 == 0)
		{
			if(dp[n/2] == -1)
			{
				ans2 = minStep(n/2, dp);
				dp[n/2] = ans2;
			}
			else
			{
				ans2 = dp[n/2];
			}		
		}
		
		if(n%3 == 0)
		{
			if(dp[n/3] == -1)
			{
				ans3 = minStep(n/3, dp);
				dp[n/3] = ans3;
			}
			else
			{
				ans3 = dp[n/3];
			}		
		}
		
		int myAns = Math.min(ans1, Math.min(ans2, ans3))+1;
		
		return myAns;		
	}
	
	
	
	
	public static int minCost(int cost[][], int i, int j)
	{
		int n = cost.length;
		int m = cost[0].length;
		
		if(i == n-1 && j== m-1)
		{
			return cost[i][j];
		}
		
		if(i>= n || j>= m)
		{
			return Integer.MAX_VALUE;
		}
		
		int ans1 = minCost(cost, i+1, j);
		int ans2 = minCost(cost, i, j+1);
		int ans3 = minCost(cost, i+1, j+1);
		
		int myAns = cost[i][j] + Math.min(ans1, Math.min(ans2, ans3));
		return myAns;
			
	}
	
	public static int minCostR(int cost[][], int i, int j, int dp[][])
	{
		int n = cost.length;
		int m = cost[0].length;
		
		if(i == n-1 && j== m-1)
		{
			return cost[i][j];
		}
		
		if(i>= n || j>= m)
		{
			return Integer.MAX_VALUE;
		}
		
		int ans1,ans2,ans3;
		
		
		if(dp[i+1][j] == Integer.MIN_VALUE)
		{
			ans1 = minCost(cost, i+1, j);
			dp[i+1][j] = ans1;
		}
		else
		{
			ans1 = dp[i+1][j];
		}
		
		if(dp[i][j+1] == Integer.MIN_VALUE)
		{
			ans2 = minCost(cost, i, j+1);
			dp[i][j+1] = ans2;			
		}
		else
		{
			ans2 = dp[i][j+1];
		}
		
		if(dp[i+1][i+1] == Integer.MIN_VALUE)
		{
			ans3 = minCost(cost, i+1, j+1);
			dp[i+1][j+1] = ans3;
		}
		else
		{
			ans3 = dp[i+1][j+1];
		}
				
		int myAns = cost[i][j] + Math.min(ans1, Math.min(ans2, ans3));
		return myAns;
				
	}
	
	public static int[][] minCostI(int cost[][])
	{
		int n = cost.length;
		int m = cost[0].length;
		
		int dp[][] = new int[n+1][m+1];
		
		for(int i=0; i<dp.length; i++)
		{
			for(int j=0; j<dp[0].length; j++)
			{
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
				
		for(int i = n-1; i>=0; i--)
		{
			for(int j = m-1; j>=0; j--)
			{
				if(i == n-1 && j == m-1)
				{
					dp[i][j] = cost[i][j];
					continue;
				}
				
				int ans1 = dp[i][j+1];
				int ans2 = dp[i+1][j];
				int ans3 = dp[i+1][j+1];
				
				dp[i][j] = cost[i][j] + Math.min(ans1, Math.min(ans2, ans3));								
			}
		}
		
		return dp;
				
	}
	
	public static int KS(int W, int wt[], int val[], int i)
	{
		if( i== val.length)
		{
			return 0;
		}	
		int ans;
		
		if(wt[i] <= W)
		{			
			int ans1 = val[i] + KS(W-wt[i], wt, val, i+1);			
			
			int ans2 = KS(W, wt, val, i+1);
			ans = Math.max(ans1, ans2);					
		}
		else
		{
			ans = KS(W, wt, val, i+1);
		}		
		return ans;		
	}
	
	public static int minChocolate(int arr[], int dp[])	
	{
		if(arr.length ==1)
		{
			return 1;
		}
	
		if(arr == null)
			return 0;
		
		
		dp[0] = 1;
		
		for(int i=1; i<arr.length; i++)
		{
			if(arr[i] > arr[i-1])
			{
				dp[i] = 1+dp[i-1];
			}			
			if(arr[i-1] >= arr[i])
			{
				dp[i] =1;
			}		
		}
        for (int i = arr.length - 2; i >= 0; i--)
        {
            if (arr[i] > arr[i + 1])
                dp[i] = Math.max(dp[i + 1] + 1, dp[i]);
            else
                dp[i] = Math.max(dp[i], 1);
        }
 		
		int sum=0;
		for(int i=0; i<dp.length; i++)
		{
			sum = sum+dp[i];
		}
		return sum;		
	}
	
	public static void main(String[] args) 
	{  
		int arr[] = takeInput();		
		int dp[] = new int[arr.length];
		for(int i=0; i<dp.length; i++)
		{
			dp[i] = -1;
		}
		
		System.out.print(minChocolate(arr, dp));
		
		
		
		
		
	 
						
	}
}





















