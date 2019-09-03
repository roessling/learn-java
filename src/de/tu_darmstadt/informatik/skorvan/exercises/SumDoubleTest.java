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

public class SumDoubleTest {
	
	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
//		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=-1;
		int b=-3;
		int act = nber.sumDouble(a, b);
		int exp= -4;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble("+a+","+b+")>", -4, nber.sumDouble(a, b));
	}

	@Test
	public void test2() {
		Number nber = new Number();
//		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=-2;
		int b=3;
		int act = nber.sumDouble(a, b);
		int exp= 1;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble(-2,3)>",1, nber.sumDouble(a, b));
	}

	@Test
	public void test3() {
		Number nber = new Number();
		int a=0;
		int b=0;
		int act = nber.sumDouble(a, b);
		int exp= 0;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble(0,0)>",0, nber.sumDouble(0, 0));
	}

	@Test
	public void test4() {
		Number nber = new Number();
		int a=-78;
		int b=78;
		int act = nber.sumDouble(a, b);
		int exp= 0;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble(-78,78)>",0, nber.sumDouble(-78, 78));
	}

	@Test
	public void test5() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=number;
		int b=2;
		int act = nber.sumDouble(a, b);
		int exp= number+2;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble(2,"+number+")>",2+number, nber.sumDouble(2, number));
	}

	@Test
	public void test6() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=number;
		int b=0;
		int act = nber.sumDouble(a, b);
		int exp= number;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble(0,"+number+")>",number, nber.sumDouble(0, number));
	}

	@Test
	public void test7() {
		Number nber = new Number();
		int a=3;
		int b=0;
		int act = nber.sumDouble(a, b);
		int exp= 3;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble(3,0)>",3, nber.sumDouble(3, 0));
	}

	@Test
	public void test8() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int a=number;
		int b=number;
		int act = nber.sumDouble(a, b);
		int exp= number*4;
		boolean res = act==(exp);
		feedLinkedlist("sumDouble("+a+","+b+")->"+exp, act, res+"");
		assertEquals("<sumDouble("+number+","+number+")>",4*number, nber.sumDouble(number, number));
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
