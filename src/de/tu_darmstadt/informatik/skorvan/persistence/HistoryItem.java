package de.tu_darmstadt.informatik.skorvan.persistence;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HistoryItem {
	
	private SimpleStringProperty functionName = null;	//0
	private SimpleStringProperty runs= null;			//1
	private SimpleStringProperty compiled= null;		//2
	private SimpleStringProperty failed= null;			//3
	
	/***
	 * empty constructor
	 */
	public HistoryItem(){
		
	}
	public HistoryItem(String function, String run, String compile, String fail){
		functionName = new SimpleStringProperty(function);
		runs = new SimpleStringProperty(run);
		compiled= new SimpleStringProperty(compile);
		failed=new SimpleStringProperty(fail);
	}
	
	/**
	 * GEt the function name
	 * @return
	 */
	public String getFunctionName() {
		return functionName.get();
	}
	/**
	 *  set the function name
	 * @param functionName the function name
	 */
	public void setFunctionName(String functionName) {
		this.functionName.set(functionName);
	}
	
	/**
	 * get how much the methods was run
	 * @return
	 */
	public String getRuns() {
		return runs.get();
	}
	
	/**
	 * set how much the method was run
	 * @param runs
	 */
	public void setRuns(String runs) {
		this.runs.set(runs); 
	}
	
	/**
	 * Get how much time the method was compiled
	 * @return
	 */
	public String getCompiled() {
		return compiled.get();
	}
	
	/**
	 * Set how much time the method was compiled
	 * @param compiled
	 */
	public void setComplied(String compiled) {
		this.compiled.set(compiled);
	}
	public String getFailed() {
		return failed.get();
	}
	public void setFailed(String failed) {
		this.failed.set(failed);
	}

}
