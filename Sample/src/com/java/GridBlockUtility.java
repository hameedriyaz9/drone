package com.java;
import java.time.LocalTime;
import java.util.List;

/**
 * @author 728332
 *
 */
public interface GridBlockUtility {
	
	/**
	 * @param items
	 * @return
	 */
	List<Order> configureItems(List<String> items);

	/**
	 * @param orders
	 * @param model
	 * @return
	 */
	List<Order> configureOrders(List<Order> orders, Model model);
	
	/**
	 * @param items
	 * @return
	 */
	List<Order> sortOrders(List<Order> items, Model model);
	
	/**
	 * @param item
	 * @return
	 */
	int findLargestElement(Order item);

	
	/**
	 * @param dispatchedOrders
	 * @param orderedItems
	 * @param i
	 */
	void configureDispatchOrders(List<Order> dispatchedOrders, List<Order> orderedItems, int i);
	
	/**
	 * @param order
	 * @param model
	 * @param time
	 */
	void dispatchOrders(Order order, Model model);

	/**
	 * @param originalOrders
	 * @param orderedItems
	 * @param dispatchedOrders
	 * @param model
	 * @param time
	 */
	void startDispatchingTheOrders(List<Order> originalOrders, List<Order> orderedItems, List<Order> dispatchedOrders,
			Model model, LocalTime time);
}
