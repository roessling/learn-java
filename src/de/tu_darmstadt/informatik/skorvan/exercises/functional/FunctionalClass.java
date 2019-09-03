package de.tu_darmstadt.informatik.skorvan.exercises.functional;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalClass {

	/***
	 * 
	 * Given a list of integers, return a list where each integer is multiplied
	 * by 2.
	 * 
	 * @param nums
	 * @return
	 */

	public List<Integer> doubling(List<Integer> nums) {

		 return nums.stream()
		 .map(n -> n * 2)
		 .collect(Collectors.toList());
	}

	/***
	 * 
	 * Given a list of integers, return a list where each integer is multiplied
	 * with itself.
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> square(List<Integer> nums) {
		
		return nums.stream().map(n-> n*n).collect(Collectors.toList());
	}

	/***
	 * Given a list of strings, return a list where each string has "*" added at
	 * its end. addStar(["a", "bb", "ccc"]) -> ["a*", "bb*", "ccc*"]
	 * 
	 * @param strings
	 * @return
	 */
	public List<String> addStar(List<String> strings) {
		return strings.stream().map(n->n+"*").collect(Collectors.toList());

	}

	/***
	 * Given a list of strings, return a list where each string is replaced by 3
	 * copies of the string concatenated together. copies3(["a", "bb", "ccc"]) ->
	 * ["aaa", "bbbbbb", "ccccccccc"]
	 * 
	 * @param strings
	 * @return
	 */
	public List<String> copies3(List<String> strings) {
		return strings.stream().map(n->n+n+n).collect(Collectors.toList());

	}
	
	/***
	 * Given a list of strings, return a list where each string has "y" added at its start and end.
	 * moreY(["hello", "there"]) -> ["yhelloy", "ytherey"]
	 * @param strings
	 * @return
	 */
	public List<String> moreY(List<String> strings) {
		return strings.stream().map(n-> "y"+n+"y").collect(Collectors.toList());
		  
	}
	
	/***
	 * Given a list of integers, return a list where each integer is added to 1 and the result is multiplied by 10.
	 * math1([6, 8, 6, 8, 1]) -> [70, 90, 70, 90, 20]
	 * @param nums
	 * @return
	 */
	public List<Integer> math1(List<Integer> nums) {
		return nums.stream().map(n-> (n+1)*10).collect(Collectors.toList());
		  
	}
	
	/***
	 * Given a list of non-negative integers, return an integer list of the rightmost digits. (Note: use %)
	 * rightDigit([16, 8, 886, 8, 1]) -> [6, 8, 6, 8, 1]
	 * @param nums
	 * @return
	 */
	public List<Integer> rightDigit(List<Integer> nums) {
		return nums.stream().map(n-> n%10).collect(Collectors.toList());
		  
	}
	
	
	/***
	 * Given a list of strings, return a list where each string is converted to lower case (Note: String toLowerCase() method).
	 * lower(["AAA", "BBB", "ccc"]) -> ["aaa", "bbb", "ccc"]
	 * @param strings
	 * @return
	 */
	public List<String> lower(List<String> strings) {
		return strings.stream().map(n-> n.toLowerCase()).collect(Collectors.toList());
		  
	}

	/***
	 * Given a list of strings, return a list where each string has all its "x" removed.
	 * noX(["xxax", "xbxbx", "xxcx"]) -> ["a", "bb", "c"]
	 * @param strings
	 * @return
	 */
	public List<String> noX(List<String> strings) {
		return strings.stream().map(n-> n.replace("x", "")).collect(Collectors.toList());  
	}


}
