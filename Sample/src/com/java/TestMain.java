package com.java;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 728332
 *
 */
public class TestMain {

	/**
	 * @param args path for the input file.
	 */
	public static void main(String[] args) {
		try {
			NpsScoreCalculator cal = new NpsScoreCalculatorImpl();
			GridBlockUtility util = new GridBlockUtilityImpl();
			FileOperations fo = new FileOperationsImpl();
			Model model = new Model();

			List<String> items = fo.readContentFromFile(args[0]);

			List<Order> originalOrders = util.configureItems(items);

			LocalTime time = LocalTime.parse("06:00:00");
			model.setLastOrdersCheckedAt(time);
			model.setOrderTimeBefore(true);
			model.setDroneDispatchedTime(time);

			List<Order> firstDepartureList = util.configureOrders(originalOrders, model);

			List<Order> orderedItems = util.sortOrders(firstDepartureList, model);
			List<Order> dispatchedOrders = new ArrayList<>();

			util.startDispatchingTheOrders(originalOrders, orderedItems, dispatchedOrders, model, time);

			int nps = cal.findNpsScore(dispatchedOrders);

			String path = System.getProperty("user.home") + "\\Desktop\\Delivered.txt";

			String message = fo.writeContentToDisk(dispatchedOrders, nps, path);
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("error --> " + e.getMessage());
		}
	}

}
