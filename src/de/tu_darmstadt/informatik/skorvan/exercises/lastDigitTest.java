package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class lastDigitTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number nber = new Number();
		int tp1 = 23;
		int tp2= 29;
		boolean act = nber.lastDigit(tp1, tp2);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("lastDigit("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<lastDigit("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		int tp1 = 10;
		int tp2= 900;
		boolean act = nber.lastDigit(tp1, tp2);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("lastDigit("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<lastDigit("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		int tp1 = 21;
		int tp2= 1;
		boolean act = nber.lastDigit(tp1, tp2);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("lastDigit("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<lastDigit("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		int tp1 = 20;
		int tp2= 95;
		boolean act = nber.lastDigit(tp1, tp2);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("lastDigit("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<lastDigit("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		int tp1 = 50;
		int tp2= 0;
		boolean act = nber.lastDigit(tp1, tp2);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("lastDigit("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<lastDigit("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		int tp1 = 503;
		int tp2= 333;
		boolean act = nber.lastDigit(tp1, tp2);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("lastDigit("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<lastDigit("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		int tp1 = 1010;
		int tp2= 100;
		boolean act = nber.lastDigit(tp1, tp2);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("lastDigit("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<lastDigit("+tp1+","+tp2+")>", exp, act);
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
