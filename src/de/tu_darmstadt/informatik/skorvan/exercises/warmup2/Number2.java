package de.tu_darmstadt.informatik.skorvan.exercises.warmup2;

public class Number2 {
	/**
	 * Given a string, return a string made of the chars at indexes 0,1, 4,5,
	 * 8,9 ... so "kittens" yields "kien".
	 * 
	 * @param str
	 * @return
	 */
	public String altPairs(String str) {
	    char[] result = new char[str.length()];
	    int count = 0;
	      
	    int i = 0;
	    int inc = 1;
	    while(i < str.length()) {
	        result[count] = str.charAt(i);
	        count++;
	        i += inc;
	                            
	        if(inc == 1)
	            inc = 3;
	        else
	            inc = 1;
	    }
	                                                    
	    return new String(result, 0, count);
	}
	/* Given an array of ints, return true if .. 1, 2, 3, .. appears in the array 
	 * somewhere.
	 */
	public boolean array123(int[] nums) {
	    for(int i = 0; i < nums.length - 2; i++) {
	        if(nums[i] == 1 && nums[i+1] == 2 && nums[i+2] == 3)
	            return true;
	    }
	    
	    return false;
	}
	/* Given an array of ints, return the number of times that two 6's are next to 
	 * each other in the array. Also count instances where the second "6" is 
	 * actually a 7.
	 */
	public int array667(int[] nums) {
	    int count = 0;
	    
	    for(int i = 0; i < nums.length - 1; i++) {
	        if(nums[i] == 6 && (nums[i+1] == 6 || nums[i+1] == 7))
	            count++;
	    }
	                    
	    return count;
	}
	/* Given an array of ints, return the number of 9's in the array.
	 */
	public int arrayCount9(int[] nums) {
	    int count = 0;
	    
	    for(int i = 0; i < nums.length; i++) {
	        if(nums[i] == 9)
	            count++;
	    }
	                    
	    return count;
	}
	/* Given an array of ints, return true if one of the first 4 elements in the 
	 * array is a 9. The array length may be less than 4.
	 */
	public boolean arrayFront9(int[] nums) {
	    int i = 0;
	    
	    while(i < nums.length	 && i < 4) {
	        if(nums[i] == 9)
	            return true;
	                      
	        i++;
	    }
	                              
	    return false;
	}
	/* Count the number of "xx" in the given string. We'll say that overlapping is 
	 * allowed, so "xxx" contains 2 "xx".
	 */
	public int countXX(String str) {
	    int count = 0;
	    for(int i = 0; i < str.length() - 1; i++) {
	        if(str.substring(i, i + 2).equals("xx"))
	            count++;
	    }
	    return count;
	}
	/* Given a string, return true if the first instance of "x" in the string is 
	 * immediately followed by another "x".
	 */
	public boolean doubleX(String str) {
	    for(int i = 0; i < str.length() - 1; i++) {
	        if(str.charAt(i) == 'x') {
	            if(str.charAt(i + 1) == 'x')
	                return true;
	            else
	                return false;
	        }
	    }
	    return false;
	}
	/* Given a string and a non-negative int n, we'll say that the front of the 
	 * string is the first 3 chars, or whatever is there if the string is less 
	 * than length 3. Return n copies of the front.
	 */
	public String frontTimes(String str, int n) {
	    char[] result;
	    String front;
	      
	    if(str.length() < 3)
	        front = str;
	    else
	        front = str.substring(0, 3);
	                      
	    result = new char[n * front.length()];
	                          
	    int index = 0;
	    for(int i = 0; i < n; i++) {
	        for(int j = 0; j < front.length(); j++) {
	            result[index] = front.charAt(j);
	            index++;
	        }
	    }
	                                                        
	    return new String(result);
	}
	/* Given an array of ints, return true if it contains a 2, 7, 1 pattern -- 
	 * a value, followed by the value plus 5, followed by the value minus 1. 
	 * Additionally the 271 counts even if the "1" differs by 2 or less from the 
	 * correct value.
	 */
	public boolean has271(int[] nums) {
	    for(int i = 0; i < nums.length - 2; i++) {
	        if((nums[i+1] == nums[i] + 5) && 
	            (Math.abs(nums[i+2] - (nums[i] - 1)) <= 2))
	            return true;
	    }
	                
	    return false;
	}
	/* Given a string, return the count of the number of times that a substring 
	 * length 2 appears in the string and also as the last 2 chars of the string, 
	 * so "hixxxhi" yields 1 (we won't count the end substring).
	 */
	public int last2(String str) {
	    if(str.length() < 2)
	        return 0;
	          
	    String end = str.substring(str.length() - 2);
	    int count = 0;
	                
	    for(int i = 0; i < str.length() - 2; i++) {
	        if(str.substring(i, i + 2).equals(end))
	            count++;
	    }
	                                
	    return count;
	}
	/* Given an array of ints, we'll say that a triple is a value appearing 3 
	 * times in a row in the array. Return true if the array does not contain any 
	 * triples.
	 */
	public boolean noTriples(int[] nums) {
	    for(int i = 0; i < nums.length - 2; i++) {
	        if(nums[i+1] == nums[i] && nums[i+2] == nums[i])
	            return false;
	    }
	                
	    return true;
	}
	/* Given a string, return a new string made of every other char starting with 
	 * the first, so "Hello" yields "Hlo".
	 */
	public String stringBits(String str) {
		String result = "";
		  // Note: the loop increments i by 2
		  for (int i=0; i<str.length(); i+=2) {
		    result = result + str.substring(i, i+1);
		    // Alternately could use str.charAt(i)
		  }
		  return result;
	}
	/* Given 2 strings, a and b, return the number of the positions where they 
	 * contain the same length 2 substring. So "xxcaazz" and "xxbaaz" yields 3, 
	 * since the "xx", "aa", and "az" substrings appear in the same place in both 
	 * strings.
	 */
	public int stringMatch(String a, String b) {
	    int min = Math.min(a.length(), b.length());
	    int count = 0;
	      
	    for(int i = 0; i < min - 1; i++) {
	        if(a.substring(i, i + 2).equals(b.substring(i, i + 2)))
	            count++;
	    }
	                      
	    return count;
	}
	/* Given a non-empty string like "Code" return a string like "CCoCodCode".
	 */
	public String stringSplosion(String str) {
	    int size = (str.length() * (str.length() + 1)) / 2;
	    char[] result = new char[size];
	      
	    int index = 0;
	    for(int i = 0; i < str.length(); i++) {
	        for(int j = 0; j <= i; j++) {
	            result[index] = str.charAt(j);
	            index++;
	        }
	    }
	                                  
	    return new String(result);
	}

	/* Given a string, return a version where all the "x" have been removed. 
	 * Except an "x" at the very start or end should not be removed.
	 */
	public String stringX(String str) {
	    if(str.length() < 2)
	        return str;
	          
	    char[] result = new char[str.length()];
	    result[0] = str.charAt(0);
	                
	    int count = 1;
	    for(int i = 1; i < str.length() - 1; i++) {
	        if(str.charAt(i) != 'x') {
	            result[count] = str.charAt(i);
	            count++;
	        }
	    }
	                                            
	    result[count] = str.charAt(str.length() - 1);
	    count++;
	    return new String(result, 0, count);
	}
	/* Suppose the string "yak" is unlucky. Given a string, return a version where 
	 * all the "yak" are removed, but the "a" can be any char. The "yak" strings 
	 * will not overlap.
	 */
	public String stringYak(String str) {
	    char[] result = new char[str.length()];
	    int count = 0;

	    for(int i = 0; i < str.length();) {
	        if(i < str.length() - 2 && str.charAt(i) == 'y' && 
	            str.charAt(i + 2) == 'k') {
	            i += 3;
	        } else {
	            result[count] = str.charAt(i);
	            count++;
	            i++;
	        }
	    } 
	                                                     
	    return new String(result, 0, count);
	}

}
