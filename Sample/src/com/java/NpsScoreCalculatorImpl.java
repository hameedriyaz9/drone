package com.java;
import java.util.List;

/**
 * @author 728332
 *
 */
public class NpsScoreCalculatorImpl implements NpsScoreCalculator {

	@Override
	public int findNpsScore(List<Order> orderedItems) {
		int promotors = 0;
		int detractors = 0;
		int neutrals = 0;
		int nps = 0;
		for (Order item : orderedItems) {
			if (item.getTotalTimeTaken() <= 90) {
				promotors += 1;
			} else if(item.getTotalTimeTaken() <= 210){
				neutrals += 1;
			} else {
				detractors += 1;
			}
		}
		nps = (promotors * 100) / orderedItems.size() - (detractors * 100) / orderedItems.size();
		return nps;
	}
	

}
