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

public class array123Test {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{1,2,3,4,5};
		boolean act = nber.array123(nums);
		boolean b = act==(result);
		feedLinkedlist("array123("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<array123("+Arrays.toString(nums)+")>", result, nber.array123(nums));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		boolean result = false;
		int[] nums = new int[]{0,9,8,6,2};
		boolean act = nber.array123(nums);
		boolean b = act==(result);
		feedLinkedlist("array123("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<array123("+Arrays.toString(nums)+")>", result, nber.array123(nums));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		boolean result = false;
		int[] nums = new int[]{3,4,5,9,6,0,9};
		boolean act = nber.array123(nums);
		boolean b = act==(result);
		feedLinkedlist("array123("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<array123("+Arrays.toString(nums)+")>", result, nber.array123(nums));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		boolean result = false;
		int[] nums = new int[]{};
		boolean act = nber.array123(nums);
		boolean b = act==(result);
		feedLinkedlist("array123("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<array123("+Arrays.toString(nums)+")>", result, nber.array123(nums));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		boolean result = false;
		int[] nums = new int[]{2,3,1,30};
		boolean act = nber.array123(nums);
		boolean b = act==(result);
		feedLinkedlist("array123("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<array123("+Arrays.toString(nums)+")>", result, nber.array123(nums));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		boolean result = true;
		int[] nums = new int[]{1,4,5,7,8,1,2,3,23,67,89,0,3};
		boolean act = nber.array123(nums);
		boolean b = act==(result);
		feedLinkedlist("array123("+Arrays.toString(nums)+")->"+result, act, b+"");
		assertEquals("<array123("+Arrays.toString(nums)+")>", result, nber.array123(nums));
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
