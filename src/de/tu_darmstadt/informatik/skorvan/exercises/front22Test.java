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
 * 
 * @author Soule Nkepseu <nkepsoul@hotmail.com>
 */
public class front22Test {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String arg = "r3";
		String act = nber.front22(arg);
		String exp = "r3r3r3";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "12";
		String act = nber.front22(arg);
		String exp = "121212";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "_wolke";
		String act = nber.front22(arg);
		String exp = "_w_wolke_w";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = "a!bc";
		String act = nber.front22(arg);
		String exp = "a!a!bca!";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "Marvin";
		String act = nber.front22(arg);
		String exp = "MaMarvinMa";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "Darmstadt";
		String act = nber.front22(arg);
		String exp = "DaDarmstadtDa";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "Aa";
		String act = nber.front22(arg);
		String exp = "AaAaAa";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		String arg = "w";
		String act = nber.front22(arg);
		String exp = "www";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp, act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test9() {
		Number nber = new Number();
		String arg = "";
		String act = nber.front22(arg);
		String exp = "";
		boolean b = act.equals(exp);
		feedLinkedlist("front22(\""+arg+"\")->"+exp+"", act, b+"");
		assertEquals("<front22(\""+arg+"\")>", exp, act);
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
