package com.diat;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class EulerPhi {
	  static long gcd(long a, long b) 
	    { 
	        if (a == 0) 
	            return b; 
	        return gcd(b % a, a); 
	    } 
	  
	    
	   static int phiFun(long n) 
	    { 
		   Instant start = Instant.now();
	        int ans= 1; 
	        for (int i = 2; i < n; i++) 
	            if (gcd(i, n) == 1) 
	                ans++; 
	        Instant finish = Instant.now();
	        long timeElapsed = Duration.between(start, finish).getSeconds();
	        System.out.println("Time Required in seconds: "+timeElapsed);
	        return ans;
	    } 
	  
	    
	    public static void main(String[] args) 
	    { 
	        long n; 
	        
	        String wish = "y";
			do{
	        System.out.println("Enter Number ");
	        
	        Scanner sc= new Scanner(System.in);
	        n=sc.nextInt();
	            System.out.println("phi(" + n + ") = "  +phiFun(n));
	            System.out.println("Do you want to continue.. (y/n)");

		        Scanner sc1= new Scanner(System.in);
		        wish=sc1.next();
		        System.out.println("wish = "+wish);
	        }while(wish.equals("y"));
	    } 
	
}
