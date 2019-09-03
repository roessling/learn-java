/**
 * 
 */
package de.tu_darmstadt.informatik.skorvan.exercises;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @author Soule Nkepseu <nkepsoul@hotmail.com>
 */
public class monkeyTroubleTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		boolean arg = false;
		boolean n=true;
		boolean act = nber.monkeyTrouble(arg, n);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("monkeyTrouble("+arg+","+n+")->"+exp, act+"", b+"");
		assertEquals("<monkeyTrouble("+arg+")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		boolean arg = true;
		boolean n=false;
		boolean act = nber.monkeyTrouble(arg, n);
		boolean exp = false;
		boolean b = act==(exp);
		feedLinkedlist("monkeyTrouble("+arg+","+n+")->"+exp, act+"", b+"");
		assertEquals("<monkeyTrouble("+arg+")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		boolean arg = true;
		boolean n=true;
		boolean act = nber.monkeyTrouble(arg, n);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("monkeyTrouble("+arg+","+n+")->"+exp, act+"", b+"");
		assertEquals("<monkeyTrouble("+arg+")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		boolean arg = false;
		boolean n=false;
		boolean act = nber.monkeyTrouble(arg, n);
		boolean exp = true;
		boolean b = act==(exp);
		feedLinkedlist("monkeyTrouble("+arg+","+n+")->"+exp, act+"", b+"");
		assertEquals("<monkeyTrouble("+arg+")>", exp, act);
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
