package com.test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.java.GridBlockUtility;
import com.java.GridBlockUtilityImpl;
import com.java.Model;
import com.java.Order;
import com.java.NpsScoreCalculator;
import com.java.NpsScoreCalculatorImpl;  

public class DroneTest {
	
	GridBlockUtility util = new GridBlockUtilityImpl();
	NpsScoreCalculator cal = new NpsScoreCalculatorImpl();
	Model model = new Model();
	
	@Test
	public void TestDroneChallenge() {
		
		List<String> items = new ArrayList<>();
		
		items.add("WM001 N11W5 05:11:50");
		items.add("WM002 S3E2 05:11:55");
		items.add("WM003 N7E50 05:31:50");
		items.add("WM004 N11E5 06:11:50");
		
		List<Order> listItems = util.configureItems(items);
		
		LocalTime time = LocalTime.parse("06:00:00");
		model.setLastOrdersCheckedAt(time);
		model.setOrderTimeBefore(true);
		model.setDroneDispatchedTime(time);
		List<Order> firstDepartureList = util.configureOrders(listItems, model);

		List<Order> orderedItems = util.sortOrders(firstDepartureList, model);
		List<Order> dispatchedOrders = new ArrayList<>();

		util.startDispatchingTheOrders(listItems, orderedItems, dispatchedOrders, model, time);

		int nps = cal.findNpsScore(dispatchedOrders);
		assertEquals(75, nps);
	}
}
