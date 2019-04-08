package com.java;
import java.util.List;

/**
 * @author 728332
 *
 */
public interface FileOperations {
	
	/**
	 * @param args
	 * @return
	 */
	public List<String> readContentFromFile(String args);
	
	/**
	 * @param items
	 * @param nps
	 * @param path
	 * @return
	 */
	public String writeContentToDisk(List<Order> items, int nps, String path);
}
