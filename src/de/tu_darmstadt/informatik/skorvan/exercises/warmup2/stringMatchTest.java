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
public class stringMatchTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number2 nber = new Number2();
		String str = "UI";
		String str2 = "UI";
		int result = 1;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String str = "J";
		String str2 = "Java-FOP";
		int result = 0;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String str = "helloworld";
		String str2 = "he";
		int result = 1;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String str = "wwaaddcc";
		String str2 = "waaauuc";
		int result = 1;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String str = "owo";
		String str2 = "oco";
		int result = 0;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String str = "voila";
		String str2 = "v";
		int result = 0;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String str = "v";
		String str2 = "voila";
		int result = 0;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		String str = "vo";
		String str2 = "voila";
		int result = 1;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test9() {
		Number2 nber = new Number2();
		String str = "voila";
		String str2 = "vo";
		int result = 1;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test99() {
		Number2 nber = new Number2();
		String str = "123456";
		String str2 = "223356";
		int result = 2;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
	}
	@Test
	public void test999() {
		Number2 nber = new Number2();
		String str = "xxcaazz";
		String str2 = "xxbaaz";
		int result = 3;
		int act = nber.stringMatch(str,str2);
		boolean b = act==(result);
		feedLinkedlist("stringMatch(\""+str+"\",\""+str2+"\")->"+result, act, b+"");
		assertEquals("<stringMatch(\""+str+"\")>", result, nber.stringMatch(str, str2));
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
//			System.out.println("PAth of the created file: "+logfile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
