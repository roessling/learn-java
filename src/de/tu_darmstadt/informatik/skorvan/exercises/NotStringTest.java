package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class NotStringTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String str = "";
		String result = "not ";
		String act= nber.notString(str);
		boolean b = act.equals(result);
		feedLinkedlist("notString(\""+str+"\")->"+result, act, b+"");
		assertEquals("<notString("+str+")>", result, nber.notString(str));
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String str = "_not";
		String result = "not _not";
		String act= nber.notString(str);
		boolean b = act.equals(result);
		feedLinkedlist("notString(\""+str+"\")->"+result, act, b+"");
		assertEquals("<notString("+str+")>", result, nber.notString(str));
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String str = " ";
		String result = "not  ";
		String act= nber.notString(str);
		boolean b = act.equals(result);
		feedLinkedlist("notString(\""+str+"\")->"+result, act, b+"");
		assertEquals("<notString("+str+")>", result, nber.notString(str));
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String str = "gdi1 dd";
		String result = "not gdi1 dd";
		String act= nber.notString(str);
		boolean b = act.equals(result);
		feedLinkedlist("notString(\""+str+"\")->"+result, act, b+"");
		assertEquals("<notString("+str+")>", result, nber.notString(str));
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String str = "noTe";
		String result = "not noTe";
		String act= nber.notString(str);
		boolean b = act.equals(result);
		feedLinkedlist("notString(\""+str+"\")->"+result, act, b+"");
		assertEquals("<notString("+str+")>", result, nber.notString(str));
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String str = "b";
		String result = "not b";
		String act= nber.notString(str);
		boolean b = act.equals(result);
		feedLinkedlist("notString(\""+str+"\")->"+result, act, b+"");
		assertEquals("<notString("+str+")>", result, nber.notString(str));
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
