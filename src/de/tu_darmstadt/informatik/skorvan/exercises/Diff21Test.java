package de.tu_darmstadt.informatik.skorvan.exercises;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.junit.AfterClass;
import org.junit.Test;

public class Diff21Test {
	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		int number = new Random().ints(-100, 20).findFirst().getAsInt();
		int result = nber.diff21(number);
		int expected = Math.abs(number - 21);
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
//		stdoutHistory.add("<diff21("+number+")> -> "+result);
		assertEquals("<diff21("+number+")>",expected, result);
		
	}

	@Test
	public void test2() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int n=21;
		int result = nber.diff21(n);
		int expected = 0;
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test3() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int n=-1;
		int result = nber.diff21(n);
		int expected = 22;
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test4() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int n=42;
		int result = nber.diff21(n);
		int expected = 42;
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test5() {
		Number nber = new Number();
		int number = new Random().ints(-100, 20).findFirst().getAsInt();
		int result = nber.diff21(number);
		int expected = Math.abs(number - 21);
//		System.out.println("random nber: "+number);
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test6() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int n=30;
		int result = nber.diff21(n);
		int expected = 18;
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test7() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int n=-21;
		int result = nber.diff21(n);
		int expected = 42;
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test8() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int n=101;
		int result = nber.diff21(n);
		int expected = 160;
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test9() {
		Number nber = new Number();
		int number = new Random().ints(4, 20).findFirst().getAsInt();
		int n=2;
		int result = nber.diff21(n);
		int expected = 19;
		feedLinkedlist("<diff21("+number+")> -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
	}

	@Test
	public void test10() {
		Number nber = new Number();
		int number = new Random().ints(22, 1000).findFirst().getAsInt();
		int result = nber.diff21(number);
		int expected = Math.abs(number - 21)*2;
//		System.out.println("random nber: "+number);
		feedLinkedlist("diff21("+number+") -> "+expected, result+"", (expected==result)+"");
		assertEquals("<diff21("+number+")>",expected, result);	
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
