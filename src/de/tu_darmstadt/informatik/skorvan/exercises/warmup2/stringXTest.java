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
public class stringXTest {

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
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String str = "oh!";
		String result = "oh!";
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String str = "xm";
		String result = "xm";
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String str = "xxCodexx";
		String result = "xCodex";
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String str = "xHallox";
		String result = "xHallox";
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String str = "xx";
		String result = "xx";
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String str = "xVitaminexx";
		String result = "xVitaminex";
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		String str = "Wasxser";
		String result = "Wasser";
		String act = nber.stringX(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringX(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringX(\""+str+"\")>", result, nber.stringX(str));
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
