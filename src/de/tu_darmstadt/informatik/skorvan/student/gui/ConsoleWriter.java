package de.tu_darmstadt.informatik.skorvan.student.gui;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.function.Consumer;

public class ConsoleWriter extends Writer {

	private Consumer<String> output;

	public ConsoleWriter(Consumer<String> output) {
		this.output = output;
	}

	@Override
	public void close() throws IOException {

	}

	@Override
	public void flush() throws IOException {

	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		output.accept(new String(Arrays.copyOfRange(cbuf, off, off + len)));
	}

}
