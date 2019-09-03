package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @author Soule Nkepseu <nkepsoul@hotmail.com>
 */
public class PosNegTest {
	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=0;
		int b=0;
		boolean neg = true;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
	}
	@Test
	public void test2() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=10;
		int b=number;
		boolean neg = true;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
	}
	@Test
	public void test3() {
		Number nber = new Number();
//		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=-1;
		int b=-4;
		boolean neg = true;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= true;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
	}
	@Test
	public void test4() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=-10;
		int b=number;
		boolean neg = false;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= true;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", true, nber.posNeg(a, b, neg));
	}
	@Test
	public void test5() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=0;
		int b=0;
		boolean neg = false;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
	}
	@Test
	public void test6() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=100;
		int b=-40;
		boolean neg = true;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
	}
	@Test
	public void test7() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=number;
		int b=2;
		boolean neg = false;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
	}
	@Test
	public void test8() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=-10;
		int b=-40;
		boolean neg = true;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= true;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
	}
	@Test
	public void test9() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=-6;
		int b=-6;
		boolean neg = false;
		boolean act = nber.posNeg(a, b, neg);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("posNeg("+a+","+b+","+neg+")->"+exp, act, res+"");
		assertEquals("<posNeg("+a+","+b+","+neg+")>", exp, nber.posNeg(a, b, neg));
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
