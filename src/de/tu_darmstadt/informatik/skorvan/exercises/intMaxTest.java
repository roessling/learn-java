package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class intMaxTest {

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
		int tp3=4;
		int act = nber.intMax(tp1, tp2, tp3);
		int exp= 79;
		boolean b = act==(exp);
		feedLinkedlist("intMax("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<intMax("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		int tp1 = 0;
		int tp2= -2;
		int tp3=-90;
		int act = nber.intMax(tp1, tp2, tp3);
		int exp= 0;
		boolean b = act==(exp);
		feedLinkedlist("intMax("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<intMax("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		int tp1 = -1;
		int tp2= 1;
		int tp3=0;
		int act = nber.intMax(tp1, tp2, tp3);
		int exp= 1;
		boolean b = act==(exp);
		feedLinkedlist("intMax("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<intMax("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		int tp1 = 0;
		int tp2= 0;
		int tp3=0;
		int act = nber.intMax(tp1, tp2, tp3);
		int exp= 0;
		boolean b = act==(exp);
		feedLinkedlist("intMax("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<intMax("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		int tp1 = 50;
		int tp2= 60;
		int tp3=100;
		int act = nber.intMax(tp1, tp2, tp3);
		int exp= 100;
		boolean b = act==(exp);
		feedLinkedlist("intMax("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<intMax("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		int tp1 = -3;
		int tp2= -5;
		int tp3=-2;
		int act = nber.intMax(tp1, tp2, tp3);
		int exp= -2;
		boolean b = act==(exp);
		feedLinkedlist("intMax("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<intMax("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		int tp1 = 1;
		int tp2= 2;
		int tp3=4;
		int act = nber.intMax(tp1, tp2, tp3);
		int exp= 4;
		boolean b = act==(exp);
		feedLinkedlist("intMax("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<intMax("+tp1+","+tp2+","+tp3+")>", exp, act);
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
