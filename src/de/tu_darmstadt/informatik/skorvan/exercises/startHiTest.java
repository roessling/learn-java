package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class startHiTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String arg = "hi";
		boolean act = nber.startHi(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "h";
		boolean act = nber.startHi(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "Hi Joe";
		boolean act = nber.startHi(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = "";
		boolean act = nber.startHi(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "hi bob";
		boolean act = nber.startHi(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "ihi Kalb";
		boolean act = nber.startHi(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "hiii Flix";
		boolean act = nber.startHi(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		String arg = "hick";
		boolean act = nber.startHi(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("startHi(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<startHi(\""+arg+"\")>", exp, act);
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
