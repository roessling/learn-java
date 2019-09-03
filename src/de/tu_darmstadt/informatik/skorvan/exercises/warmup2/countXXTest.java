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
public class countXXTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test() {
		Number2 nber = new Number2();
		String arg = "Java";
		int act = nber.countXX(arg);
		int exp = 0;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number2 nber = new Number2();
		String arg = "JavaXX";
		int act = nber.countXX(arg);
		int exp = 0;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number2 nber = new Number2();
		String arg = "xxGrundlage";
		int act = nber.countXX(arg);
		int exp = 1;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number2 nber = new Number2();
		String arg = "axbxcxdx";
		int act = nber.countXX(arg);
		int exp = 0;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number2 nber = new Number2();
		String arg = "xxWir zaehlen x";
		int act = nber.countXX(arg);
		int exp = 1;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number2 nber = new Number2();
		String arg = "xxx";
		int act = nber.countXX(arg);
		int exp = 2;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number2 nber = new Number2();
		String arg = "xXxWindows";
		int act = nber.countXX(arg);
		int exp = 0;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
	}
	
	@Test
	public void test8() {
		Number2 nber = new Number2();
		String arg = "Exxxxtention";
		int act = nber.countXX(arg);
		int exp = 3;
		boolean b = act==(exp);
		feedLinkedlist("countXX(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<countXX(\""+arg+"\")>", exp, act);
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
