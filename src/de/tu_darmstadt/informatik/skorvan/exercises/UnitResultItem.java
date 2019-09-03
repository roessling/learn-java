package de.tu_darmstadt.informatik.skorvan.exercises;

import javafx.beans.property.SimpleStringProperty;

/**
 * The Structure of the unit result 
 * given (expected, actually, bewertung) -> erwartet (das eigentliche Ergebnis), ergebnis von Studentencode and Green or red as bewertung
 * @author Soule Nkepseu
 *
 */
public class UnitResultItem {
	
	private SimpleStringProperty expected;
	private SimpleStringProperty actually;
	private SimpleStringProperty result;
	public UnitResultItem(String exp, String act, String res){
		expected = new SimpleStringProperty(exp);
		actually = new SimpleStringProperty(act);
		result = new SimpleStringProperty(res);
	}
	public String getExpected() {
		return expected.get();
	}
	public void setExpected(String expected) {
		this.expected.set(expected);
	}
	public String getActually() {
		return actually.get();
	}
	public void setActually(String actually) {
		this.actually.set(actually);
	}
	public String getResult() {
		return result.get();
	}
	public void setResult(String result) {
		this.result.set(result);
	}

}
