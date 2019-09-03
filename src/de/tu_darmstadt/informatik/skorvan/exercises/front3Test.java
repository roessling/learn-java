package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class front3Test {
	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test() {
		Number nber = new Number();
		String arg = "Java";
		String act = nber.front3(arg);
		String exp = "JavJavJav";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "abc";
		String act = nber.front3(arg);
		String exp = "abcabcabc";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "";
		String act = nber.front3(arg);
		String exp = "";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = " ";
		String act = nber.front3(arg);
		String exp = "   ";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "c";
		String act = nber.front3(arg);
		String exp = "ccc";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "-";
		String act = nber.front3(arg);
		String exp = "---";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "xBnOp3";
		String act = nber.front3(arg);
		String exp = "xBnxBnxBn";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		String arg = "!!-!";
		String act = nber.front3(arg);
		String exp = "!!-!!-!!-";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test9() {
		Number nber = new Number();
		String arg = "Beliebig";
		String act = nber.front3(arg);
		String exp = "BelBelBel";
		boolean b = act.equals(exp);
		feedLinkedlist("front3(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front3(\""+arg+"\")>", exp, act);
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
