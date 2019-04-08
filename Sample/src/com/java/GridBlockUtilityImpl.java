package com.java;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 728332
 *
 */
public class GridBlockUtilityImpl implements GridBlockUtility {

	/**
	 * minScorePromotors
	 */
	private static int minScorePromotors = 90;
	
	/**
	 * minScoreNeutrals
	 */
	private static int minScoreNeutrals = 210;

	@Override
	public List<Order> sortOrders(List<Order> orders, Model model) {
		/** Sorting based on traveling distance */
		orders = orders.stream().sorted(Comparator.comparing(Order::getTravelDistance)).collect(Collectors.toList());
		
		/** Sorting based on waiting time */
		orders.sort(new Comparator<Order>() {
		    @Override
		    public int compare(Order o1, Order o2) {
		    	int i = 0;
		    	LocalTime l1 = o1.getOrderTime();
	    		LocalTime l2 = model.getDroneDispatchedTime().plusMinutes((long) o1.getTravelDistance());
	    		long o1Duration = Duration.between(l1, l2).toMinutes();
	    		
	    		LocalTime l3 = o2.getOrderTime();
	    		LocalTime l4 = model.getDroneDispatchedTime().plusMinutes((long) o2.getTravelDistance());
	    		long o2Duration = Duration.between(l3, l4).toMinutes();
	    		
	    		if(o1Duration==o2Duration) {
	    			return o1.getTravelDistance() < o2.getTravelDistance() ? -1:1;
	    		} else if(o1Duration < o2Duration) {
	    			long finalDuration = Duration.between(l3, l2.plusMinutes((long) o2.getTravelDistance())).toMinutes();
    				if((o2.getWaitingTimeinMinutes() < 90 && finalDuration < 90) || (o2.getWaitingTimeinMinutes() < 210 && finalDuration < 210)) {
    					i = -1;
    				} else {
    					i = 1;
    				}
    			} else {
    				i = 1;
    			}
	    		return i;
		    }
		});
		
		return orders;
	}

	@Override
	public List<Order> configureItems(List<String> items) {
		List<Order> itemList = new ArrayList<>();
		for (String item : items) {
			Order i = new Order();
			i.setOrderId(item.split(" ")[0]);
			String latlng = item.split(" ")[1];
			i.setDirection(latlng.split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])"));
			LocalTime time = LocalTime.parse(item.split(" ")[2]);
			i.setOrderTime(time);
			itemList.add(i);
		}
		return itemList;
	}

	@Override
	public int findLargestElement(Order order) {
		int biggest = 0;
		if (Integer.parseInt(order.getDirection()[1]) == Integer.parseInt(order.getDirection()[3])) {
			biggest = Integer.parseInt(order.getDirection()[1]);
		} else {
			biggest = Integer.parseInt(order.getDirection()[1]) > Integer.parseInt(order.getDirection()[3])
					? Integer.parseInt(order.getDirection()[1])
					: Integer.parseInt(order.getDirection()[3]);
		}
		return biggest;
	}

	@Override
	public List<Order> configureOrders(List<Order> orders, Model model) {
		List<Order> departureList = new ArrayList<>();
		for (Order order : orders) {
			int largest = this.findLargestElement(order);
			double totalDistance = largest * 2;
			order.setTravelDistance(totalDistance);
			int time = model.getDroneDispatchedTime().compareTo(order.getOrderTime());
			if (order.getOrderTime().equals(model.getLastOrdersCheckedAt()) || model.isOrderTimeBefore()?order.getOrderTime().isBefore(model.getLastOrdersCheckedAt()):order.getOrderTime().isAfter(model.getLastOrdersCheckedAt()) && time>0 && order.getOrderTime().isBefore(LocalTime.parse("22:00:00"))) {
				order.setWaitingTimeinMinutes(minScorePromotors - totalDistance);
				if (order.getWaitingTimeinMinutes() > 0) {
					order.setOrderFlag("p");
				} else if (Math.abs(order.getWaitingTimeinMinutes()) + order.getTravelDistance() < minScoreNeutrals) {
					order.setWaitingTimeinMinutes(Math.abs(order.getWaitingTimeinMinutes()) + order.getTravelDistance());
					order.setOrderFlag("N");
				} else {
					order.setOrderFlag("D");
				}
				departureList.add(order);
			}
		}
		return departureList;
	}

	@Override
	public void dispatchOrders(Order dispatchOrder, Model model) {
		dispatchOrder.setDispatchTime(model.getDroneDispatchedTime());
		model.setDroneReturnedTime(model.getDroneDispatchedTime().plusMinutes((long) dispatchOrder.getTravelDistance()));
		model.setDroneDispatchedTime(model.getDroneReturnedTime().plusMinutes(2));
		LocalTime l1 = dispatchOrder.getDispatchTime().plusMinutes((long) dispatchOrder.getTravelDistance());
		LocalTime l2 = dispatchOrder.getOrderTime();
		long totalTimeTaken = Duration.between(l2, l1).toMinutes();
		dispatchOrder.setTotalTimeTaken(Math.abs(totalTimeTaken));
	}

	@Override
	public void configureDispatchOrders(List<Order> dispatchedOrders, List<Order> orderedItems, int i) {
		dispatchedOrders.add(orderedItems.get(i));
		orderedItems.remove(i);
	}
	
	@Override
	public void startDispatchingTheOrders(List<Order> originalOrders, List<Order> orderedItems, List<Order> dispatchedOrders, Model model, LocalTime time) {
		int i = 0;
		boolean flag = false;
		while (!orderedItems.isEmpty()) {
			this.dispatchOrders(orderedItems.get(i), model);
			this.configureDispatchOrders(dispatchedOrders, orderedItems, i);
			if (!flag) {
				model.setLastOrdersCheckedAt(time);
			}
			model.setOrderTimeBefore(false);
			List<Order> newOrders = this.configureOrders(originalOrders, model);
			for (Order order : newOrders) {
				orderedItems.add(order);
			}
			if (newOrders.size() > 0) {
				Optional<Order> maxTime = newOrders.stream().max(Comparator.comparing(Order::getOrderTime));
				if (maxTime.isPresent()) {
					model.setLastOrdersCheckedAt(maxTime.get().getOrderTime());
					flag = true;
				}
				List<Order> newOrderedItems = this.sortOrders(orderedItems, model);
				orderedItems = this.sortOrders(newOrderedItems, model);
			}
		}
	}

}
