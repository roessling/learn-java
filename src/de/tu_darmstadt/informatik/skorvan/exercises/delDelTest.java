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
public class delDelTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String arg = "deHello";
		String act = nber.delDel(arg);
		String exp = "deHello";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "";
		String act = nber.delDel(arg);
		String exp = "";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "dl";
		String act = nber.delDel(arg);
		String exp = "dl";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = "ndel";
		String act = nber.delDel(arg);
		String exp = "n";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "dedelel";
		String act = nber.delDel(arg);
		String exp = "dedelel";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "Hdelallo";
		String act = nber.delDel(arg);
		String exp = "Hallo";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "delegate";
		String act = nber.delDel(arg);
		String exp = "delegate";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		String arg = "led";
		String act = nber.delDel(arg);
		String exp = "led";
		boolean b = act.equals(exp);
		feedLinkedlist("delDel(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<delDel(\""+arg+"\")>", exp, act);
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
