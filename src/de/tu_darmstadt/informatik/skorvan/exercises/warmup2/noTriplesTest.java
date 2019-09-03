/**
 * 
 */
package de.tu_darmstadt.informatik.skorvan.exercises.warmup2;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @author Soule Nkepseu <nkepsoul@hotmail.com>
 */
public class noTriplesTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@AfterClass
	public static void ShowResult(){
		saveTestValues();
//		stdoutHistory.stream().forEach(System.out::println);
	}
	@Test
	public void test1() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{1,2,7,1};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{1,2,3};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		boolean result = false;
		int[] nums = new int[]{2,2,2};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{2,7,1,30};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{1,4,5,7,8,1,2,7,1,67,89,0,3};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{2,7,4,2,7,2,7,2,7,1};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{9,9,-9};
		boolean act = nber.noTriples(nums);
		boolean b = act==(result);
		feedLinkedlist("noTriples("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<noTriples("+Arrays.toString(nums)+")>", result, nber.noTriples(nums));
	}
    private static void saveTestValues(){
    	try {
    		String tempDir = System.getProperty("java.io.tmpdir");
            File logfile = new File(tempDir + "/data.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(logfile));
			for(String str: stdoutHistory){
				bw.write(str);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
