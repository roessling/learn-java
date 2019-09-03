package de.tu_darmstadt.informatik.skorvan.teacher.gui;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import de.tu_darmstadt.informatik.skorvan.persistence.Task;

public interface TeacherCallback {

	public void newTask();

	public boolean openTasks(List<Path> taskFile, boolean temp);

	/**
	 * 
	 * @param data
	 *            array of strings, depending on type: all types: Title,
	 *            Description, Code Prefix, Code Suffix, Test File type 2:
	 *            Method Name, Method Parameters... type 3: Class Name
	 * @param location
	 *            the file to save to
	 * @return true if successful, false otherwise
	 */
	public boolean saveTask(String[] data, Path location);

	// save to last location. If no last location is given, call gui for a
	// location
	public boolean saveTask(String[] data);

	public void testTask();

	// see saveTask for data description
	public boolean checkChanged(String[] data);

	public boolean exportTasks(Task[] tasks, File zipLocation);
	
	public boolean importTasks(List<File> zips);
	
	public void showTask(Task task);

}