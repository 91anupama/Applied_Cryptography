package com.diat;

public class DesKeyGen {
	
	private static class DES { 
        // first key-hePermutation Table 
    int[] PC1 = { 57, 49, 41, 33, 25, 
                  17, 9, 1, 58, 50, 42, 34, 26, 
                  18, 10, 2, 59, 51, 43, 35, 27, 
                  19, 11, 3, 60, 52, 44, 36, 63, 
                  55, 47, 39, 31, 23, 15, 7, 62, 
                  54, 46, 38, 30, 22, 14, 6, 61, 
                  53, 45, 37, 29, 21, 13, 5, 28, 
                  20, 12, 4 }; 

    // second key-Permutation Table 
    int[] PC2 = { 14, 17, 11, 24, 1, 5, 3, 
                  28, 15, 6, 21, 10, 23, 19, 12, 
                  4, 26, 8, 16, 7, 27, 20, 13, 2, 
                  41, 52, 31, 37, 47, 55, 30, 40, 
                  51, 45, 33, 48, 44, 49, 39, 56, 
                  34, 53, 46, 42, 50, 36, 29, 32 }; 



int[] shiftBits = { 1, 1, 2, 2, 2, 2, 2, 2, 
                        1, 2, 2, 2, 2, 2, 2, 1 }; 



        // hexadecimal to binary conversion 
    String hextoBin(String input) 
    { 
        int n = input.length() * 4; 
        input = Long.toBinaryString( 
            Long.parseUnsignedLong(input, 16)); 
        while (input.length() < n) 
            input = "0" + input; 
        return input; 
    } 
    

    // binary to hexadecimal conversion 
    String binToHex(String input) 
    { 
        int n = (int)input.length() / 4; 
        input = Long.toHexString( 
            Long.parseUnsignedLong(input, 2)); 
        while (input.length() < n) 
            input = "0" + input; 
        return input; 
    } 
    String permutation(int[] sequence, String input) 
    { 
        String output = ""; 
        input = hextoBin(input); 
        for (int i = 0; i < sequence.length; i++) 
            output += input.charAt(sequence[i] - 1); 
        output = binToHex(output); 
        return output; 
    } 

    // left Circular Shifting bits 
    String leftCircularShift(String input, int numBits) 
    { 
        int n = input.length() * 4; 
        int perm[] = new int[n]; 
        for (int i = 0; i < n - 1; i++) 
            perm[i] = (i + 2); 
        perm[n - 1] = 1; 
        while (numBits-- > 0) 
            input = permutation(perm, input); 
        return input; 
    } 
    

 // preparing 16 keys for 16 rounds 
    String[] getKeys(String key) 
    { 
        String keys[] = new String[16]; 
        // first key permutation 
        String inputBin=hextoBin(key);
        System.out.println("Initial binary key : "+inputBin+"\n");
        
        key = permutation(PC1, key);
        String keyWithoutParity=hextoBin(key);
        System.out.println("Key After dropping parity bits : "+keyWithoutParity+"\n"); 	
  
        for (int i = 0; i < 16; i++) { 
            key = leftCircularShift( 
                      key.substring(0, 7), shiftBits[i]) 
                  + leftCircularShift(key.substring(7, 14), 
                                      shiftBits[i]); 
            // second key permutation 
            keys[i] = permutation(PC2, key); 
        } 
        return keys; 
    } 

        String encrypt(String key) 
    { 
        int i; 
        // get round keys 
        String keys[] = getKeys(key);
	
                // 16 rounds 
        for (i = 0; i < 16; i++) { 
           
    System.out.println(i+1 +" Round : "+hextoBin(keys[i])+"\n"); 
        }
		return key; 

}


}




  public static void main(String args[]) 
{ 
    
    String key = "AABB09182736CCDD"; 

    DES cipher = new DES(); 
    System.out.println("Encryption 16 keys:\n"); 
   String  outKey = cipher.encrypt(key); 
  // System.out.println(outKey);
     
} 


}
