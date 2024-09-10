package com.diat;

import java.util.Scanner;

public class ExtendedEuclidean {
	 public void solve(long a, long b)
	    {
	        long m = 0, n = 1, lastm = 1, lastn = 0, temp;
	        while (b != 0)
	        {
	            long q = a / b;
	            long r = a % b;
	 
	            a = b;
	            b = r;
	 
	            temp = m;
	            m = lastm - q * m;
	            lastm = temp;
	 
	            temp = n;
	            n = lastn - q * n;
	            lastn = temp;            
	        }
	        System.out.println("m:"+ lastm +"\nn:"+lastn+"\nGCD : "+a);
	    }
	
	    public static void main (String[] args) 
	    {
	        Scanner scan = new Scanner(System.in);
	        System.out.println("*****Extended Euclidean Algorithm******\n");
	       
	        ExtendedEuclidean ee = new ExtendedEuclidean();
	 
	        System.out.println("Enter a b of ma + nb = gcd(a, b)\n");
	        long a = scan.nextLong();
	        long b = scan.nextLong();
	       
	        ee.solve(a, b);        
	    }
}
