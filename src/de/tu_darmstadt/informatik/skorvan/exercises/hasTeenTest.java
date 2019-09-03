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
public class hasTeenTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		Number nber = new Number();
		int tp1 = 23;
		int tp2= 29;
		int tp3=4;
		boolean act = nber.hasTeen(tp1, tp2, tp3);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("hasTeen("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<hasTeen("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test2() {
		Number nber = new Number();
		int tp1 = 23;
		int tp2= 14;
		int tp3=4;
		boolean act = nber.hasTeen(tp1, tp2, tp3);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("hasTeen("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<hasTeen("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test3() {
		Number nber = new Number();
		int tp1 = 0;
		int tp2= 12;
		int tp3=4;
		boolean act = nber.hasTeen(tp1, tp2, tp3);
		boolean exp= false;
		boolean b = act==(exp);
		feedLinkedlist("hasTeen("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<hasTeen("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test4() {
		Number nber = new Number();
		int tp1 = 13;
		int tp2= 20;
		int tp3=400;
		boolean act = nber.hasTeen(tp1, tp2, tp3);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("hasTeen("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<hasTeen("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test5() {
		Number nber = new Number();
		int tp1 = 19;
		int tp2= 99;
		int tp3=4;
		boolean act = nber.hasTeen(tp1, tp2, tp3);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("hasTeen("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<hasTeen("+tp1+","+tp2+","+tp3+")>", exp, act);
	}
	@Test
	public void test6() {
		Number nber = new Number();
		int tp1 = 79;
		int tp2= 18;
		int tp3=4;
		boolean act = nber.hasTeen(tp1, tp2, tp3);
		boolean exp= true;
		boolean b = act==(exp);
		feedLinkedlist("hasTeen("+tp1+","+tp2+","+tp3+")->"+exp, act, b+"");
		assertEquals("<hasTeen("+tp1+","+tp2+","+tp3+")>", exp, act);
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
