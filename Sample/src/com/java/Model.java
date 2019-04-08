package com.java;

import java.time.LocalTime;

/**
 * @author 728332
 *
 */
public class Model {
	
	/**
	 * droneDispatchedTime
	 */
	private LocalTime droneDispatchedTime;
	
	/**
	 * droneReturnedTime
	 */
	private LocalTime droneReturnedTime;
	
	/**
	 * lastOrdersCheckedAt
	 */
	private LocalTime lastOrdersCheckedAt;

	/**
	 * isOrderTimeBefore
	 */
	private boolean isOrderTimeBefore;
	
	/**
	 * @return
	 */
	public LocalTime getDroneReturnedTime() {
		return droneReturnedTime;
	}

	/**
	 * @param droneReturnedTime
	 */
	public void setDroneReturnedTime(LocalTime droneReturnedTime) {
		this.droneReturnedTime = droneReturnedTime;
	}

	/**
	 * @return
	 */
	public LocalTime getDroneDispatchedTime() {
		return droneDispatchedTime;
	}

	/**
	 * @param droneDispatchedTime
	 */
	public void setDroneDispatchedTime(LocalTime droneDispatchedTime) {
		this.droneDispatchedTime = droneDispatchedTime;
	}

	/**
	 * @return
	 */
	public LocalTime getLastOrdersCheckedAt() {
		return lastOrdersCheckedAt;
	}

	/**
	 * @param lastOrdersCheckedAt
	 */
	public void setLastOrdersCheckedAt(LocalTime lastOrdersCheckedAt) {
		this.lastOrdersCheckedAt = lastOrdersCheckedAt;
	}

	/**
	 * @return
	 */
	public boolean isOrderTimeBefore() {
		return isOrderTimeBefore;
	}

	/**
	 * @param isOrderTimeBefore
	 */
	public void setOrderTimeBefore(boolean isOrderTimeBefore) {
		this.isOrderTimeBefore = isOrderTimeBefore;
	}

}
