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

public class NearHundredTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		
		int a=99;
		boolean act=nber.nearHundred(a);
		boolean b = act==(true);
		feedLinkedlist("nearHundred(\""+a+"\")->"+true, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", true, nber.nearHundred(a));
	}
	@Test
	public void test1() {
		Number nber = new Number();
		
		int a=41;
		boolean act=nber.nearHundred(a);
		boolean b = act==(false);
		feedLinkedlist("nearHundred(\""+a+"\")->"+false, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", false, nber.nearHundred(a));
	}
	@Test
	public void test3() {
		Number nber = new Number();
		
		int a=100;
		boolean act=nber.nearHundred(a);
		boolean b = act==(true);
		feedLinkedlist("nearHundred(\""+a+"\")->"+true, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", true, nber.nearHundred(a));
	}
	@Test
	public void test4() {
		Number nber = new Number();
		
		int a=-109;
		boolean act=nber.nearHundred(a);
		boolean b = act==(false);
		feedLinkedlist("nearHundred(\""+a+"\")->"+false, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", false, nber.nearHundred(a));
	}
	@Test
	public void test5() {
		Number nber = new Number();
		
		int a=189;
		boolean act=nber.nearHundred(a);
		boolean b = act==(false);
		feedLinkedlist("nearHundred(\""+a+"\")->"+false, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", false, nber.nearHundred(a));
	}
	@Test
	public void test6() {
		Number nber = new Number();
		
		int a=191;
		boolean act=nber.nearHundred(a);
		boolean b = act==(true);
		feedLinkedlist("nearHundred(\""+a+"\")->"+true, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", true, nber.nearHundred(a));
	}
	@Test
	public void test7() {
		Number nber = new Number();
		
		int a=202;
		boolean act=nber.nearHundred(a);
		boolean b = act==(true);
		feedLinkedlist("nearHundred(\""+a+"\")->"+true, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", true, nber.nearHundred(a));
	}
	@Test
	public void test8() {
		Number nber = new Number();
		
		int a=-209;
		boolean act=nber.nearHundred(a);
		boolean b = act==(false);
		feedLinkedlist("nearHundred(\""+a+"\")->"+false, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", false, nber.nearHundred(a));
	}
	@Test
	public void test9() {
		Number nber = new Number();
		
		int a=-110;
		boolean act=nber.nearHundred(a);
		boolean b = act==(false);
		feedLinkedlist("nearHundred(\""+a+"\")->"+false, act+"", b+"");
		assertEquals("<nearHundred("+a+")>", false, nber.nearHundred(a));
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
