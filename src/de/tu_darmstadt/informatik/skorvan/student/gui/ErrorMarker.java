package de.tu_darmstadt.informatik.skorvan.student.gui;

public interface ErrorMarker {

	// mark an error between characters from start to end. Returns the line
	// number.
	public int mark(int start, int end);

	public void show();

	public void mark(int startLine, int startCol, int endLine, int endCol);
	
	public void markWarning(int startLine, int startCol, int endLine, int endCol);

}
