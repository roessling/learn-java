package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class stringETest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String arg = "hie";
		boolean act = nber.stringE(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "h";
		boolean act = nber.stringE(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "e";
		boolean act = nber.stringE(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = "";
		boolean act = nber.stringE(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "eXeXee";
		boolean act = nber.stringE(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "ihi Kalb";
		boolean act = nber.stringE(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "uhe!";
		boolean act = nber.stringE(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		String arg = "ricee";
		boolean act = nber.stringE(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("stringE(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<stringE(\""+arg+"\")>", exp, act);
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
