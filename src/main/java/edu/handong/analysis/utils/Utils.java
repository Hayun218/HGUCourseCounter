package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

	public static ArrayList<String> getLines(String file, boolean removeHeader) {

		ArrayList<String> readfile = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null)
				readfile.add(line);
			
			if (removeHeader)
				readfile.remove(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readfile;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		try {
			File dest = null;
			dest = new File(targetFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(dest));
			if (!dest.exists()) dest.createNewFile();
			for(String line : lines)
				bw.write(line);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
