package com.diat;

import java.util.Scanner;

public class PlayfairCipherDecryption {

	 private String KeyWord        = new String();
	    private String Key            = new String();
	    private char   matrix_arr[][] = new char[6][6];
	 
	    public void setKey(String k)
	    {
	        String K_adjust = new String();
	        boolean flag = false;
	        K_adjust = K_adjust + k.charAt(0);
	        for (int i = 1; i < k.length(); i++)
	        {
	            for (int j = 0; j < K_adjust.length(); j++)
	            {
	                if (k.charAt(i) == K_adjust.charAt(j))
	                {
	                    flag = true;
	                }
	            }
	            if (flag == false)
	                K_adjust = K_adjust + k.charAt(i);
	            flag = false;
	        }
	        KeyWord = K_adjust;
	    }
	 
	    public void KeyGen()
	    {
	        boolean flag = true;
	        char current;
	        int num=48;
	        Key = KeyWord;
	        for (int i = 0; i < 36; i++)
	        {
	        	//CHECK FOR NUMBERS BETWWEN 0-9 48<=I>=57
	        	
	        	 int ascii=i+48;
	        	//if((ascii>=48)&&(ascii<=57))
	           
	           // if (current == 'j')
	               // continue;
	           
	            if((ascii>=58)&&(ascii<=90)) {
	            	
	            	current = (char) (i + 48+7);
	            	ascii=i + num+7;
	            }
	            else
	            	 current = (char) (i + 48);//65
	        
	            for (int j = 0; j < KeyWord.length(); j++)
	            {
	                if (current == KeyWord.charAt(j))
	                {
	                    flag = false;
	                    break;
	                }
	            }
	            if (flag)
	                Key = Key + current;
	            flag = true;
	        }
	        System.out.println(Key);
	        matrix();
	    }
	 
	    private void matrix()
	    {
	        int counter = 0;
	        for (int i = 0; i < 6; i++)
	        {
	            for (int j = 0; j < 6; j++)
	            {
	                matrix_arr[i][j] = Key.charAt(counter);
	                System.out.print(matrix_arr[i][j] + " ");
	                counter++;
	            }
	            System.out.println();
	        }
	    }
	 
	    private String format(String old_text)
	    {
	        int i = 0;
	        int len = 0;
	        String text = new String();
	        len = old_text.length();
	        for (int tmp = 0; tmp < len; tmp++)
	        {
			/*
			 * if (old_text.charAt(tmp) == 'j') { text = text + 'i'; } else
			 */
	                text = text + old_text.charAt(tmp);
	        }
	        len = text.length();
	        for (i = 0; i < len; i = i + 2)
	        {
	            if (text.charAt(i + 1) == text.charAt(i))
	            {
	                text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
	            }
	        }
	        return text;
	    }
	 
	    private String[] Divid2Pairs(String new_string)
	    {
	        String Original = format(new_string);
	        int size = Original.length();
	        if (size % 2 != 0)
	        {
	            size++;
	            Original = Original + 'x';
	        }
	        String x[] = new String[size / 2];
	        int counter = 0;
	        for (int i = 0; i < size / 2; i++)
	        {
	            x[i] = Original.substring(counter, counter + 2);
	            counter = counter + 2;
	        }
	       // System.out.println(x);
	        return x;
	    }
	 
	    public int[] GetDiminsions(char letter)
	    {
	        int[] key = new int[2];
		/*
		 * if (letter == 'j') letter = 'i';
		 */
	        for (int i = 0; i < 6; i++)
	        {
	            for (int j = 0; j < 6; j++)
	            {
	                if (matrix_arr[i][j] == letter)
	                {
	                    key[0] = i;
	                    key[1] = j;
	                    break;
	                }
	            }
	        }
	        return key;
	    }
	 
	    public String encryptMessage(String Source)
	    {
	        String src_arr[] = Divid2Pairs(Source);
	        String Code = new String();
	        char one;
	        char two;
	        int part1[] = new int[2];
	        int part2[] = new int[2];
	        for (int i = 0; i < src_arr.length; i++)
	        {
	            one = src_arr[i].charAt(0);
	            two = src_arr[i].charAt(1);
	            part1 = GetDiminsions(one);
	            part2 = GetDiminsions(two);
	            if (part1[0] == part2[0])
	            {
	                if (part1[1] < 4)
	                    part1[1]++;
	                else
	                    part1[1] = 0;
	                if (part2[1] < 4)
	                    part2[1]++;
	                else
	                    part2[1] = 0;
	            }
	            else if (part1[1] == part2[1])
	            {
	                if (part1[0] < 4)
	                    part1[0]++;
	                else
	                    part1[0] = 0;
	                if (part2[0] < 4)
	                    part2[0]++;
	                else
	                    part2[0] = 0;
	            }
	            else
	            {
	                int temp = part1[1];
	                part1[1] = part2[1];
	                part2[1] = temp;
	            }
	            Code = Code + matrix_arr[part1[0]][part1[1]]
	                    + matrix_arr[part2[0]][part2[1]];
	        }
	        return Code;
	    }
	 
	    public String decryptMessage(String Code)
	    {
	        String Original = new String();
	        String src_arr[] = Divid2Pairs(Code);
	        char one;
	        char two;
	        int part1[] = new int[2];
	        int part2[] = new int[2];
	        for (int i = 0; i < src_arr.length; i++)
	        {
	            one = src_arr[i].charAt(0);
	            two = src_arr[i].charAt(1);
	            part1 = GetDiminsions(one);
	            part2 = GetDiminsions(two);
	            if (part1[0] == part2[0])
	            {
	                if (part1[1] > 0)
	                    part1[1]--;
	                else
	                    part1[1] = 4;
	                if (part2[1] > 0)
	                    part2[1]--;
	                else
	                    part2[1] = 4;
	            }
	            else if (part1[1] == part2[1])
	            {
	                if (part1[0] > 0)
	                    part1[0]--;
	                else
	                    part1[0] = 4;
	                if (part2[0] > 0)
	                    part2[0]--;
	                else
	                    part2[0] = 4;
	            }
	            else
	            {
	                int temp = part1[1];
	                part1[1] = part2[1];
	                part2[1] = temp;
	            }
	            Original = Original + matrix_arr[part1[0]][part1[1]]
	                    + matrix_arr[part2[0]][part2[1]];
	        }
	        return Original;
	    }
	 
	    public static void main(String[] args)
	    {
	        PlayfairCipherDecryption x = new PlayfairCipherDecryption();
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter a keyword:");
	        String keyword = sc.next();
	        x.setKey(keyword);
	        x.KeyGen();
	        System.out
	                .println("Enter even word to encrypt: ");
	        String key_input = sc.next();
	        if (key_input.length() % 2 == 0)
	        {
	            System.out.println("Encryption: " + x.encryptMessage(key_input));
	            System.out.println("Decryption: "
	                    + x.decryptMessage(x.encryptMessage(key_input)));
	        }
	        else
	        {
	            System.out.println("Message length should be even");
	        }
	        sc.close();
	    }
}
