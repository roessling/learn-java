package de.tu_darmstadt.informatik.skorvan.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
	
	public static void cleanUp(Path basePath) {
		if (basePath.toFile().isDirectory()) {
			for(File f: basePath.toFile().listFiles())
				cleanUp(f.toPath());
			if (basePath.toFile().list().length == 0) {
				try {
					Files.delete(basePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
