package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class close10Test {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		int tp1 = 79;
		int tp2= 18;
		int exp = nber.close10(tp1, tp2);
		int act= 18;
		boolean b = act==(exp);
		feedLinkedlist("close10("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<close10("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		int tp1 = 10;
		int tp2= 10;
		int act = nber.close10(tp1, tp2);
		int exp= 0;
		boolean b = act==(exp);
		feedLinkedlist("close10("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<close10("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		int tp1 = 11;
		int tp2= 9;
		int act = nber.close10(tp1, tp2);
		int exp= 0;
		boolean b = act==(exp);
		feedLinkedlist("close10("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<close10("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		int tp1 = 12;
		int tp2= 9;
		int act = nber.close10(tp1, tp2);
		int exp= 9;
		boolean b = act==(exp);
		feedLinkedlist("close10("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<close10("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		int tp1 = 110;
		int tp2= -90;
		int act = nber.close10(tp1, tp2);
		int exp= 0;
		boolean b = act==(exp);
		feedLinkedlist("close10("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<close10("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		int tp1 = 20;
		int tp2= 1;
		int act = nber.close10(tp1, tp2);
		int exp= 1;
		boolean b = act==(exp);
		feedLinkedlist("close10("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<close10("+tp1+","+tp2+")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		int tp1 = 600;
		int tp2= -500;
		int act = nber.close10(tp1, tp2);
		int exp= -500;
		boolean b = act==(exp);
		feedLinkedlist("close10("+tp1+","+tp2+")->"+exp, act, b+"");
		assertEquals("<close10("+tp1+","+tp2+")>", exp, act);
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
