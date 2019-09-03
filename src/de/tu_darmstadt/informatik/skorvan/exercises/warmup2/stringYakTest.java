/**
 * 
 */
package de.tu_darmstadt.informatik.skorvan.exercises.warmup2;

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
public class stringYakTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number2 nber = new Number2();
		String str = "";
		String result = "";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String str = "oh!";
		String result = "oh!";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String str = "yak";
		String result = "";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String str = "yakwwwDk";
		String result = "wwwDk";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String str = "Hayakllo";
		String result = "Hallo";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String str = "yokBank";
		String result = "Bank";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String str = "yokFyikOykkP";
		String result = "FOP";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		String str = "yahoypko";
		String result = "yahoo";
		String act = nber.stringYak(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringYak(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringYak(\""+str+"\")>", result, nber.stringYak(str));
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
