package de.tu_darmstadt.informatik.skorvan.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Soule Nkepseu <nkepsoul@hotmail.com>
 */
public class FileUtility {
	
	public static final String separator = "§";
	public static final String fileHistory = "compilehistory";
	/***
	 * @param path the path where the file should be created.
	 * @return true is the file is/was created elsew
	 */
	private static boolean createFile(String path){
		boolean isCreated = false;
		File file = new File(path);
		if(file.exists()){
			 System.out.println("file already exist: "+ file.getAbsolutePath());
		isCreated = !isCreated;
		}
		else{
			try {
				file.createNewFile();
				isCreated = !isCreated;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("An error occured while creating the file: "+ e.getMessage());
			}
		}
		return isCreated;
	}
	/***
	 * @param path the path of the file to be read.
	 * @return a list with all the lines in the file and null if the file doesnt exist or it's empty.
	 */
	private static List<String> readFile(String path){
		List<String> tmpList = null;
			try {
				tmpList = Files.readAllLines(Paths.get(path));
				tmpList.stream().forEach(System.out::println);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("The file is not found in the given path: "+ path+"::"+e.getMessage());
			} catch (Exception e) {
				System.out.println("An unknow error occured "+ path+"::"+e.getMessage());
			}
		return tmpList;
	}
	
	private static boolean deleteFile(String path){
		
		return true;
	}
	
	/***
	 * @param text The line to append in the file.
	 * @param path  The path of the file where we should add the line
	 * @return true if the line have been added or false if not.
	 */
	private static boolean WriteaLineToFile(String text){
		String currentDir = System.getProperty("user.dir");
		String path = currentDir+"\\"+fileHistory;

		boolean isAppend = false;
		if(createFile(path)){
			File tmpFile = new File(path);
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile, true));
				writer.append(System.lineSeparator());
				writer.append(text);
				writer.close();
				isAppend = true;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return isAppend;
	}
	
	/***
	 * @param line the line to be deserialize
	 * @return
	 */
	private static HistoryItem convertLineInHItem(String line){
		HistoryItem item = null;
		String[] items = line.split(separator);
		item.setFunctionName(items[0]);
		item.setRuns(items[1]);
		item.setComplied(items[2]);
		item.setFailed(items[3]);
		
		return item;
	}
	
	/***
	 * @param item : the attribute that should be converted in a line with separator
	 * @return the line wilth all info.
	 */
	public static String convertHItemInLine(HistoryItem item){
		String result = item.getFunctionName()+separator+item.getRuns()+separator+item.getCompiled()+separator+item.getFailed();
		System.out.println(result);
		return result;
	}
}
