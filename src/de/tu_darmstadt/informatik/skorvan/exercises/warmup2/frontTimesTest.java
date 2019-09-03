package de.tu_darmstadt.informatik.skorvan.exercises.warmup2;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class frontTimesTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number2 nber = new Number2();
		String arg = "w";
		int arg2 = 3;
		String act = nber.frontTimes(arg, arg2);
		String exp = "www";
		boolean b = act.equals(exp);
		feedLinkedlist("frontTimes(\""+arg+"\","+arg2+")->"+exp, act, b+"");
		assertEquals("<frontTimes(\""+arg+"\","+arg2+")>", exp, act);
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String arg = "lou";
		int arg2 = 0;
		String act = nber.frontTimes(arg, arg2);
		String exp = "";
		boolean b = act.equals(exp);
		feedLinkedlist("frontTimes(\""+arg+"\","+arg2+")->"+exp, act, b+"");
		assertEquals("<frontTimes(\""+arg+"\","+arg2+")>", exp, act);
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String arg = "";
		int arg2 = 10;
		String act = nber.frontTimes(arg, arg2);
		String exp = "";
		boolean b = act.equals(exp);
		feedLinkedlist("frontTimes(\""+arg+"\","+arg2+")->"+exp, act, b+"");
		assertEquals("<frontTimes(\""+arg+"\","+arg2+")>", exp, act);
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String arg = "Mineral";
		int arg2 = 4;
		String act = nber.frontTimes(arg, arg2);
		String exp = "MinMinMinMin";
		boolean b = act.equals(exp);
		feedLinkedlist("frontTimes(\""+arg+"\","+arg2+")->"+exp, act, b+"");
		assertEquals("<frontTimes(\""+arg+"\","+arg2+")>", exp, act);
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String arg = "cv";
		int arg2 = 5;
		String act = nber.frontTimes(arg, arg2);
		String exp = "cvcvcvcvcv";
		boolean b = act.equals(exp);
		feedLinkedlist("frontTimes(\""+arg+"\","+arg2+")->"+exp, act, b+"");
		assertEquals("<frontTimes(\""+arg+"\","+arg2+")>", exp, act);
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String arg = "av";
		int arg2 = 5;
		String act = nber.frontTimes(arg, arg2);
		String exp = "avavavavav";
		boolean b = act.equals(exp);
		feedLinkedlist("frontTimes(\""+arg+"\","+arg2+")->"+exp, act, b+"");
		assertEquals("<frontTimes(\""+arg+"\","+arg2+")>", exp, act);
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String arg = "Sommerfest";
		int arg2 = 2;
		String act = nber.frontTimes(arg, arg2);
		String exp = "SomSom";
		boolean b = act.equals(exp);
		feedLinkedlist("frontTimes(\""+arg+"\","+arg2+")->"+exp, act, b+"");
		assertEquals("<frontTimes(\""+arg+"\","+arg2+")>", exp, act);
	}
	
	@AfterClass
	public static void ShowResult(){
		saveTestValues();
		stdoutHistory.stream().forEach(System.out::println);
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
