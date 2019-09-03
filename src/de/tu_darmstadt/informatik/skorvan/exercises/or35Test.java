package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;
/**
 * 
 * @author Soule Nkepseu <nkepsoul@hotmail.com>
 */
public class or35Test {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		int arg = 3;
		boolean act = nber.or35(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		int arg = 0;
		boolean act = nber.or35(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		int arg = 99;
		boolean act = nber.or35(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		int arg = 21;
		boolean act = nber.or35(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		int arg = 32;
		boolean act = nber.or35(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		int arg = 300;
		boolean act = nber.or35(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		int arg = 7;
		boolean act = nber.or35(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		int arg = 77;
		boolean act = nber.or35(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test9() {
		Number nber = new Number();
		int arg = 94;
		boolean act = nber.or35(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test99() {
		Number nber = new Number();
		int arg = 37;
		boolean act = nber.or35(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test89() {
		Number nber = new Number();
		int arg = 51;
		boolean act = nber.or35(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("or35(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<or35(\""+arg+"\")>", exp, act);
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
