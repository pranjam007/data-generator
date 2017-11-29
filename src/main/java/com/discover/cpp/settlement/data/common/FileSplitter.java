package com.discover.cpp.settlement.data.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.util.ResourceUtils;

public class FileSplitter {

	public static void splitFile(Path bigFile, int maxRows) throws IOException {

		int i = 1;
		try (BufferedReader reader = Files.newBufferedReader(bigFile)) {
			String line = null;
			int lineNum = 1;

			Path splitFile = Paths.get(i + "_sdi.txt");
			BufferedWriter writer = Files.newBufferedWriter(splitFile, StandardOpenOption.CREATE);

			while ((line = reader.readLine()) != null) {

				if (lineNum > maxRows) {
					writer.close();
					lineNum = 1;
					i++;
					splitFile = Paths.get(i + "_sdi.txt");
					writer = Files.newBufferedWriter(splitFile, StandardOpenOption.CREATE);
				}

				writer.append(line);
				writer.newLine();
				lineNum++;
			}

			writer.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("Split file");
			splitFile(Paths.get(ResourceUtils.toURI(ResourceUtils.getURL("C:\\Projects\\generatedData_0.csv"))),312500);
			System.out.println("Generated Files");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
