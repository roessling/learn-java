package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class backAroundTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test() {
		Number nber = new Number();
		String arg = "Java";
		String act = nber.backAround(arg);
		String exp = "aJavaa";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "a";
		String act = nber.backAround(arg);
		String exp = "aaa";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "5";
		String act = nber.backAround(arg);
		String exp = "555";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = "Scrum";
		String act = nber.backAround(arg);
		String exp = "mScrumm";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "moseS";
		String act = nber.backAround(arg);
		String exp = "SmoseSS";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "Wq-";
		String act = nber.backAround(arg);
		String exp = "-Wq--";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "xBnOp3";
		String act = nber.backAround(arg);
		String exp = "3xBnOp33";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		String arg = "Bundes";
		String act = nber.backAround(arg);
		String exp = "sBundess";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test9() {
		Number nber = new Number();
		String arg = "l Odysse";
		String act = nber.backAround(arg);
		String exp = "el Odyssee";
		boolean b = act.equals(exp);
		feedLinkedlist("backAround(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<backAround(\""+arg+"\")>", exp, act);
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
