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
public class arrayCount9Test {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number2 nber = new Number2();
		int result = 0;
		int[] nums = new int[]{1,2,6,4,7};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		int result = 1;
		int[] nums = new int[]{1,2,9,6,4,5};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		int result = 2;
		int[] nums = new int[]{9,2,6,9,7,99};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		int result = 3;
		int[] nums = new int[]{9,9,7,6,9,0};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		int result = 1;
		int[] nums = new int[]{9,7,6};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		int result = 1;
		int[] nums = new int[]{6,0,6,7,6,9};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		int result = 2;
		int[] nums = new int[]{9,999,99,-9,6,9,6,5};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		int result =11;
		int[] nums = new int[]{9,9,9,9,9,9,9,9,9,9,9,0};
		int act = nber.arrayCount9(nums);
		boolean b = act==(result);
		feedLinkedlist("arrayCount9("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<arrayCount9("+Arrays.toString(nums)+")>", result, nber.arrayCount9(nums));
	}
	
	@AfterClass
	public static void ShowResult(){
		saveTestValues();
//		stdoutHistory.stream().forEach(System.out::println);
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
//			System.out.println("PAth of the created file: "+logfile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
