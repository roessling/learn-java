package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class MissingCharTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number nber = new Number();
		String str = "234s";
		int n= 3;
		String result = "234";
		String act = nber.missingChar(str,n);
		boolean b = act.equals(result);
		feedLinkedlist("missingChar("+str+","+n+")->"+result, act, b+"");
		assertEquals("<missingChar("+str+","+n+")>", result, nber.missingChar(str,n));
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String str = "..";
		int n= 0;
		String result = ".";
		String act = nber.missingChar(str,n);
		boolean b = act.equals(result);
		feedLinkedlist("missingChar("+str+","+n+")->"+result, act, b+"");
		assertEquals("<missingChar("+str+","+n+")>", result, nber.missingChar(str,n));
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String str = " ";
		int n= 0;
		String result = "";
		String act = nber.missingChar(str,n);
		boolean b = act.equals(result);
		feedLinkedlist("missingChar("+str+","+n+")->"+result, act, b+"");
		assertEquals("<missingChar("+str+","+n+")>", result, nber.missingChar(str,n));
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String str = "123456";
		int n= 5;
		String result = "12345";
		String act = nber.missingChar(str,n);
		boolean b = act.equals(result);
		feedLinkedlist("missingChar("+str+","+n+")->"+result, act, b+"");
		assertEquals("<missingChar("+str+","+n+")>", result, nber.missingChar(str,n));
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String str = "123456o9_2";
		int n= 8;
		String result = "123456o92";
		String act = nber.missingChar(str,n);
		boolean b = act.equals(result);
		feedLinkedlist("missingChar("+str+","+n+")->"+result, act, b+"");
		assertEquals("<missingChar("+str+","+n+")>", result, nber.missingChar(str,n));
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String str = "chart";
		int n= 4;
		String result = "char";
		String act = nber.missingChar(str,n);
		boolean b = act.equals(result);
		feedLinkedlist("missingChar("+str+","+n+")->"+result, act, b+"");
		assertEquals("<missingChar("+str+","+n+")>", result, nber.missingChar(str,n));
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
