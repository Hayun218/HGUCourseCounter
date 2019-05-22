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

		try {
			File fileRead = new File(file);
			if (!fileRead.exists())
				throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null)
				readfile.add(line);
			br.close();

			if (removeHeader)
				readfile.remove(0);

		} catch (

		FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughArgumentException e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return readfile;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		try {
			File dest = new File(targetFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(dest));
			if (!dest.exists())
				dest.createNewFile();
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
