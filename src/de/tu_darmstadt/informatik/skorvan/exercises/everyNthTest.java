/**
 * 
 */
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
 * @author Soule Nkepseu <nkepsoul@hotmail.com>
 */
public class everyNthTest {
	
	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String arg = "Java";
		int n=3;
		String act = nber.everyNth(arg, n);
		String exp = "Ja";
		boolean b = act.equals(exp);
		feedLinkedlist("everyNth(\""+arg+"\"+"+","+n+")->"+exp, act, b+"");
		assertEquals("<everyNth(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "Systemstart";
		int n=5;
		String act = nber.everyNth(arg, n);
		String exp = "Smt";
		boolean b = act.equals(exp);
		feedLinkedlist("everyNth(\""+arg+"\"+"+","+n+")->"+exp, act, b+"");
		assertEquals("<everyNth(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "Programm";
		int n=3;
		String act = nber.everyNth(arg, n);
		String exp = "Pgm";
		boolean b = act.equals(exp);
		feedLinkedlist("everyNth(\""+arg+"\"+"+","+n+")->"+exp, act, b+"");
		assertEquals("<everyNth(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = "Collegeblock";
		int n=4;
		String act = nber.everyNth(arg, n);
		String exp = "Cel";
		boolean b = act.equals(exp);
		feedLinkedlist("everyNth(\""+arg+"\"+"+","+n+")->"+exp, act, b+"");
		assertEquals("<everyNth(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "Ereignisse";
		int n=50;
		String act = nber.everyNth(arg, n);
		String exp = "E";
		boolean b = act.equals(exp);
		feedLinkedlist("everyNth(\""+arg+"\"+"+","+n+")->"+exp, act, b+"");
		assertEquals("<everyNth(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "Ereignisse";
		int n=2;
		String act = nber.everyNth(arg, n);
		String exp = "Eegis";
		boolean b = act.equals(exp);
		feedLinkedlist("everyNth(\""+arg+"\"+"+","+n+")->"+exp, act, b+"");
		assertEquals("<everyNth(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "Ereignisse";
		int n=7;
		String act = nber.everyNth(arg, n);
		String exp = "Es";
		boolean b = act.equals(exp);
		feedLinkedlist("everyNth(\""+arg+"\"+"+","+n+")->"+exp, act, b+"");
		assertEquals("<everyNth(\""+arg+"\")>", exp, act);
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
