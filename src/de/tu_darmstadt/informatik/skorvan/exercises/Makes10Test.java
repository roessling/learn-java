package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Test;

public class Makes10Test {
	
	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number nber = new Number();
//		 
		int a=10;
		int b=40;
		boolean act= nber.makes10(a, b);
		boolean exp=true;
		boolean res = act==(exp);
		feedLinkedlist("makes10("+a+","+b+")->"+exp, "true", res+"");
		assertEquals("<makes10("+a+","+b+")>", exp, nber.makes10(a, b));
	}
	@Test
	public void test2() {
		Number nber = new Number();
//		 
		int a=1;
		int b=9;
		boolean act= nber.makes10(a, b);
		boolean exp=true;
		boolean res = act==(exp);
		feedLinkedlist("makes10("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<makes10("+a+","+b+")>", exp, nber.makes10(a, b));
	}
	@Test
	public void test3() {
		Number nber = new Number();
//		 
		int a=3;
		int b=40;
		boolean act= nber.makes10(a, b);
		boolean exp=false;
		boolean res = act==(exp);
		feedLinkedlist("makes10("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<makes10("+a+","+b+")>", exp, nber.makes10(a, b));
	}
	@Test
	public void test4() {
		Number nber = new Number();
//		 
		int a=-3;
		int b=-7;
		boolean act= nber.makes10(a, b);
		boolean exp=false;
		boolean res = act==(exp);
		feedLinkedlist("makes10("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<makes10("+a+","+b+")>", exp, nber.makes10(a, b));
	}
	@Test
	public void test5() {
		Number nber = new Number();
//		 
		int a=90;
		int b=20;
		boolean act= nber.makes10(a, b);
		boolean exp=false;
		boolean res = act==(exp);
		feedLinkedlist("makes10("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<makes10("+a+","+b+")>", exp, nber.makes10(a, b));
	}
	@Test
	public void test6() {
		Number nber = new Number();
//		 
		int a=20;
		int b=-30;
		boolean act= nber.makes10(a, b);
		boolean exp=false;
		boolean res = act==(exp);
		feedLinkedlist("makes10("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<makes10("+a+","+b+")>", exp, nber.makes10(a, b));
	}
	@Test
	public void test7() {
		Number nber = new Number();
//		 
		int a=1000;
		int b=-990;
		boolean act= nber.makes10(a, b);
		boolean exp=true;
		boolean res = act==(exp);
		feedLinkedlist("makes10("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<makes10("+a+","+b+")>", exp, nber.makes10(a, b));
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
