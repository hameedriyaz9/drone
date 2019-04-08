package com.java;
import java.util.List;

/**
 * @author 728332
 *
 */
public interface NpsScoreCalculator {

	/**
	 * @param orderedItems
	 * @return
	 */
	public int findNpsScore(List<Order> orderedItems);
}
