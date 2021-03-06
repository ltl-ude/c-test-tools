package de.unidue.ltl.ctest.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import de.unidue.ltl.ctest.core.CTestObject;
import de.unidue.ltl.ctest.util.IOSModelVersion;
import de.unidue.ltl.ctest.util.Transformation;

public class CTestIOSWriter implements CTestWriter {
	
	private IOSModelVersion version;
	
	public CTestIOSWriter() {
		this.version = IOSModelVersion.CURRENT;
	}
	
	public CTestIOSWriter(IOSModelVersion version) {
		this.version = version;
	}
	
	@Override
	public void write(CTestObject ctest, Path filePath) throws IOException {
		if (filePath.toFile().isDirectory())
			throw new IOException("Input path is a directory, not a file.");
		
		File outFile = filePath.toFile();
		if (!outFile.exists()) {
			outFile.getParentFile().mkdirs();
			outFile.createNewFile();
		}
		
		String docText = ctest.getTokens().stream()
				.map(token -> Transformation.toIOSFormat(token, version))
				.collect(Collectors.joining(" "));
		
		Files.write(filePath, docText.getBytes());
	}

	@Override
	public void write(CTestObject ctest, String filePath) throws IOException {
		this.write(ctest, Paths.get(filePath));

	}

	@Override
	public void write(CTestObject ctest, File file) throws IOException {
		this.write(ctest, file.getAbsolutePath());
	}
}
