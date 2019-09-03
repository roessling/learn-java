package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class FrontBackTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String str = "";
		String result = "";
		String act = nber.frontBack(str);
		boolean b = act.equals(result);
		feedLinkedlist("frontBack(\""+str+"\")->"+result, act, b+"");
		assertEquals("<frontBack("+str+")>", result, nber.frontBack(str));
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String str = " .";
		String result = ". ";
		String act = nber.frontBack(str);
		boolean b = act.equals(result);
		feedLinkedlist("frontBack(\""+str+"\")->"+result, act, b+"");
		assertEquals("<frontBack("+str+")>", result, nber.frontBack(str));
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String str = "schreibWelt";
		String result = "tchreibWels";
		String act = nber.frontBack(str);
		boolean b = act.equals(result);
		feedLinkedlist("frontBack(\""+str+"\")->"+result, act, b+"");
		assertEquals("<frontBack("+str+")>", result, nber.frontBack(str));
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String str = " anna";
		String result = "aann ";
		String act = nber.frontBack(str);
		boolean b = act.equals(result);
		feedLinkedlist("frontBack(\""+str+"\")->"+result, act, b+"");
		assertEquals("<frontBack("+str+")>", result, nber.frontBack(str));
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String str = "D";
		String result = "D";
		String act = nber.frontBack(str);
		boolean b = act.equals(result);
		feedLinkedlist("frontBack(\""+str+"\")->"+result, act, b+"");
		assertEquals("<frontBack("+str+")>", result, nber.frontBack(str));
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String str = "CC";
		String result = "CC";
		String act = nber.frontBack(str);
		boolean b = act.equals(result);
		feedLinkedlist("frontBack(\""+str+"\")->"+result, act, b+"");
		assertEquals("<frontBack("+str+")>", result, nber.frontBack(str));
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
