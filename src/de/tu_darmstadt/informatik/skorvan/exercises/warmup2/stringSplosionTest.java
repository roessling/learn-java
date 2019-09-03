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
public class stringSplosionTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number2 nber = new Number2();
		String str = "Ui";
		String result = "UUi";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String str = "oh!";
		String result = "oohoh!";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String str = "m";
		String result = "m";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String str = "Code";
		String result = "CCoCodCode";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String str = "There";
		String result = "TThTheTherThere";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String str = "Wahl";
		String result = "WWaWahWahl";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String str = "Bad";
		String result = "BBaBad";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test8() {
		Number2 nber = new Number2();
		String str = "Bye";
		String result = "BByBye";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
	}
	@Test
	public void test9() {
		Number2 nber = new Number2();
		String str = "Good";
		String result = "GGoGooGood";
		String act = nber.stringSplosion(str);
		boolean b = act.equals(result);
		feedLinkedlist("stringSplosion(\""+str+"\")->"+result, act, b+"");
		assertEquals("<stringSplosion(\""+str+"\")>", result, nber.stringSplosion(str));
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
