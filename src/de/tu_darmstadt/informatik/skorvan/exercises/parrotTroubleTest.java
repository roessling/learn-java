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

public class parrotTroubleTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		Number nber = new Number();
		
		boolean talking = true;
		int hour = 10;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
	}
	@Test
	public void test8() {
		Number nber = new Number();
		
		boolean talking = true;
		int hour = 22;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= true;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
	}
	@Test
	public void test2() {
		Number nber = new Number();
		
		boolean talking = true;
		int hour = 2;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= true;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
	}
	@Test
	public void test3() {
		Number nber = new Number();
		
		boolean talking = true;
		int hour = 20;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
	}
	@Test
	public void test4() {
		Number nber = new Number();
		
		boolean talking = false;
		int hour = 23;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
	}
	@Test
	public void test5() {
		Number nber = new Number();
		
		boolean talking = false;
		int hour = 20;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
	}
	@Test
	public void test6() {
		Number nber = new Number();
		
		boolean talking = true;
		int hour = 7;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= false;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
	}
	@Test
	public void test7() {
		Number nber = new Number();
		
		boolean talking = true;
		int hour = 1;
		boolean act = nber.parrotTrouble(talking, hour);
		boolean exp= true;
		boolean res = act==exp;
		feedLinkedlist("parrotTrouble("+talking+","+hour+")->"+exp, act, res+"");
		assertEquals("<parrotTrouble("+talking+","+hour+")>", exp ,nber.parrotTrouble(talking, hour));
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
