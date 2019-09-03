package de.tu_darmstadt.informatik.skorvan.exercises.warmup2;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class doubleXTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number2 nber = new Number2();
		String arg = "xxhi";
		boolean act = nber.doubleX(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String arg = "xWasser";
		boolean act = nber.doubleX(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String arg = "voila!xx";
		boolean act = nber.doubleX(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String arg = "Z einstellung xx";
		boolean act = nber.doubleX(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String arg = "xITx";
		boolean act = nber.doubleX(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String arg = "xbxbxb";
		boolean act = nber.doubleX(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String arg = "";
		boolean act = nber.doubleX(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		String arg = "x";
		boolean act = nber.doubleX(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test9() {
		Number2 nber = new Number2();
		String arg = "XXhighest";
		boolean act = nber.doubleX(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("doubleX(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<doubleX(\""+arg+"\")>", exp, act);
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
