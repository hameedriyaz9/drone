package com.java;

import java.time.LocalTime;

/**
 * @author 728332
 *
 */
public class Order {

	/**
	 * orderId
	 */
	private String orderId;
	
	/**
	 * direction
	 */
	private String[] direction;
	
	/**
	 * orderTime
	 */
	private LocalTime orderTime;
	
	/**
	 * deliveredTime
	 */
	private LocalTime dispatchTime;
	
	private double travelDistance;
	
	private double waitingTimeinMinutes;
	
	private String orderFlag;
	
	private double totalTimeTaken;
	
	/**
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return
	 */
	public String[] getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 */
	public void setDirection(String[] direction) {
		this.direction = direction;
	}

	/**
	 * @return
	 */
	public LocalTime getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime
	 */
	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public double getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistance(double travelDistance) {
		this.travelDistance = travelDistance;
	}

	public double getWaitingTimeinMinutes() {
		return waitingTimeinMinutes;
	}

	public void setWaitingTimeinMinutes(double waitingTimeinMinutes) {
		this.waitingTimeinMinutes = waitingTimeinMinutes;
	}

	public LocalTime getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(LocalTime dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public double getTotalTimeTaken() {
		return totalTimeTaken;
	}

	public void setTotalTimeTaken(double totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}
	
	
}
