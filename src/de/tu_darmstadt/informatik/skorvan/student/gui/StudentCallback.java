package de.tu_darmstadt.informatik.skorvan.student.gui;

import java.io.File;
import java.util.List;

import de.tu_darmstadt.informatik.skorvan.persistence.Task;

public interface StudentCallback {

	public void loadTask(Task task);

	public void save();

	public void run();

	public void reset();
	
	public void importTasks(List<File> files);

}
