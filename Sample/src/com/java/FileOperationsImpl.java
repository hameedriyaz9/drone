package com.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 728332
 *
 */
public class FileOperationsImpl implements FileOperations {

	/* (non-Javadoc)
	 * @see FileOperations#readContentFromFile(java.lang.String)
	 */
	@Override
	public List<String> readContentFromFile(String args) {
		File file = new File(args);

		List<String> items = new ArrayList<>();
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		while (sc.hasNextLine()) {
			items.add(sc.nextLine());
		}
		return items;
	}

	/* (non-Javadoc)
	 * @see FileOperations#writeContentToDisk(java.util.List, int, java.lang.String)
	 */
	@Override
	public String writeContentToDisk(List<Order> items, int nps, String path) {
		FileWriter fw;
		String str= "";
		try {
			fw = new FileWriter(path);
			items.forEach(item -> {
				try {
					fw.write(item.getOrderId() + " " + item.getDispatchTime());
					fw.write(System.getProperty("line.separator"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});
			fw.write("nps " + String.valueOf(nps + "%"));
			fw.close();
			str = "Delivered orders has been downloaded to the path " + path;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return str;
	}

}
