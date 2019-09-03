package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class mixStartTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		String arg = "pix wasser";
		boolean act = nber.mixStart(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		String arg = "nix";
		boolean act = nber.mixStart(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		String arg = "ix";
		boolean act = nber.mixStart(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		String arg = "";
		boolean act = nber.mixStart(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		String arg = "mixing";
		boolean act = nber.mixStart(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		String arg = "fixing";
		boolean act = nber.mixStart(arg);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		Number nber = new Number();
		String arg = "XiXk";
		boolean act = nber.mixStart(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		Number nber = new Number();
		String arg = "riz carlton";
		boolean act = nber.mixStart(arg);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("mixStart(\""+arg+"\")->"+exp, act+"", b+"");
		assertEquals("<mixStart(\""+arg+"\")>", exp, act);
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
