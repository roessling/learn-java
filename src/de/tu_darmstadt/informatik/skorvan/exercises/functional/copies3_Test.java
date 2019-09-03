package de.tu_darmstadt.informatik.skorvan.exercises.functional;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

public class copies3_Test {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("phi");
		List<String> act = nber.copies3(arg);
		List<String> exp = Arrays.asList("phiphiphi");
		boolean b = act.equals(exp);
		feedLinkedlist("copies3(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<copies3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("");
		List<String> act = nber.copies3(arg);
		List<String> exp = Arrays.asList("");
		boolean b = act.equals(exp);
		feedLinkedlist("copies3(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<copies3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("3","0", "5");
		List<String> act = nber.copies3(arg);
		List<String> exp = Arrays.asList("333","000", "555");
		boolean b = act.equals(exp);
		feedLinkedlist("copies3(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<copies3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("Ja");
		List<String> act = nber.copies3(arg);
		List<String> exp = Arrays.asList("JaJaJa");
		boolean b = act.equals(exp);
		feedLinkedlist("copies3(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<copies3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("okay ");
		List<String> act = nber.copies3(arg);
		List<String> exp = Arrays.asList("okay okay okay ");
		boolean b = act.equals(exp);
		feedLinkedlist("copies3(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<copies3(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("ich", "bin", "joe");
		List<String> act = nber.copies3(arg);
		List<String> exp = Arrays.asList("ichichich", "binbinbin", "joejoejoe");
		boolean b = act.equals(exp);
		feedLinkedlist("copies3(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<copies3(\""+arg+"\")>", exp, act);
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
			System.out.println("PAth of the created file: "+logfile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
