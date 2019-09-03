package de.tu_darmstadt.informatik.skorvan.persistence;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * this class contains methods to pack and unpack ZIP files
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class Zipper {

	private static final int BUFFER_SIZE = 4096;

	/**
	 * unpack a file into a given folder
	 * 
	 * @param zip
	 *            the .zip file to unpack
	 * @param targetFolder
	 *            the folder to write the unpacked files to
	 * @return true if the unpacking was successful, false otherwise
	 */
	public static boolean unzip(File zip, Path targetFolder) {
		try (ZipInputStream in = new ZipInputStream(new FileInputStream(zip))) {
			ZipEntry entry;
			while ((entry = in.getNextEntry()) != null) {
				File target = targetFolder.resolve(entry.getName()).toFile();
				if (!entry.isDirectory()) {
					if (!target.exists())
						target.createNewFile();
					if (!writeToFile(in, target)) {
						System.err.println("could not write to file " + target.toString());
						return false;
					}
				} else if (!target.mkdirs()) {
					System.err.println("could not create folder " + target.toString());
					return false;
				}
				in.closeEntry();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * helper method to write a file
	 * 
	 * @param in
	 *            the stream to read from
	 * @param target
	 *            the file to write to
	 * @return true if the file was written, false otherwise
	 */
	private static boolean writeToFile(ZipInputStream in, File target) {
		if (!target.exists())
			try {
				target.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target))) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int read = 0;
			while ((read = in.read(buffer)) != -1)
				out.write(buffer, 0, read);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * pack files and folders into a .zip file
	 * 
	 * @param zip
	 *            the .zip file to pack to
	 * @param names
	 *            an Iterable of names of the entries to be made in the zip file
	 * @param data
	 *            an Iterable of paths representing files and folders named by
	 *            "names" in the same order
	 * @return true if the packing was successful, false otherwise
	 */
	public static boolean zip(File zip, Iterable<String> names, Iterable<Path> data) {
		try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip))) {
			Iterator<String> namesIt = names.iterator();
			Iterator<Path> dataIt = data.iterator();
			while (namesIt.hasNext() && dataIt.hasNext()) {
				ZipEntry ze = new ZipEntry(namesIt.next());
				out.putNextEntry(ze);
				if (!readFromFile(out, dataIt.next()))
					return false;
			}
			out.closeEntry();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * helper method to read a file and write it to a ZipOutputStream
	 * 
	 * @param out
	 *            the stream to write to
	 * @param file
	 *            the file to read from
	 * @return true if the file was written to the stream, false otherwise
	 */
	private static boolean readFromFile(ZipOutputStream out, Path file) {
		if (file.toFile().isDirectory())
			return true;
		try (FileInputStream in = new FileInputStream(file.toFile())) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int read = 0;
			while ((read = in.read(buffer)) != -1)
				out.write(buffer, 0, read);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
