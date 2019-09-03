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
public class icyHotTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number nber = new Number();
		int tp1 = 23;
		int tp2= 69;
		boolean act = nber.icyHot(tp1, tp2);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("icyHot("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<icyHot("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		int tp1 = 0;
		int tp2= 100;
		boolean act = nber.icyHot(tp1, tp2);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("icyHot("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<icyHot("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		int tp1 = -3;
		int tp2= 100;
		boolean act = nber.icyHot(tp1, tp2);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("icyHot("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<icyHot("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		int tp1 = -1;
		int tp2= 110;
		boolean act = nber.icyHot(tp1, tp2);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("icyHot("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<icyHot("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		int tp1 = -1;
		int tp2= -110;
		boolean act = nber.icyHot(tp1, tp2);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("icyHot("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<icyHot("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		int tp1 = -1000;
		int tp2= 1140;
		boolean act = nber.icyHot(tp1, tp2);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("icyHot("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<icyHot("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		int tp1 = 0;
		int tp2= 0;
		boolean act = nber.icyHot(tp1, tp2);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("icyHot("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<icyHot("+tp1+","+tp2+")>", exp, act);
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
