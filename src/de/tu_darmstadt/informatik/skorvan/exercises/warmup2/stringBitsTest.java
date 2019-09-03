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
public class stringBitsTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number2 nber = new Number2();
		String str = "UI";
		String result = "U";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String str = "";
		String result = "";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String str = "m";
		String result = "m";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String str = "ab";
		String result = "a";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String str = "bauhaus";
		String result = "buas";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String str = "bundeswehr";
		String result = "bnewh";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String str = "0123456789";
		String result = "02468";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		String str = "mineral wasser";
		String result = "mnrlwse";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test9() {
		Number2 nber = new Number2();
		String str = "tetmam";
		String result = "tta";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
	}
	@Test
	public void test99() {
		Number2 nber = new Number2();
		String str = "-h452o-";
		String result = "-42-";
		String act = nber.stringBits(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringBits(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringBits(\""+str+"\")>", result, nber.stringBits(str));
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
