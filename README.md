# drone

1) Based on the given inputs and description of the challenge, we assume the distance is 2 times of the maximum direction for an each order as the town is organized in a perfect grid.

	N7W3 - 7 is maximum, so total distance the drone need to travel the blocks is 14.
	
2) As the target of our challenge is maximizing the nps score, we consider maximum waiting time for each orders. 

Assumptions:

3) Considering maximum wait time for promotors is 90 Min., neutrals is 210 Min. and Detractors is greater than 210 Min.

4) Maximum waiting time for each order is calculated as 90(promotors) - distance for delivery.

	For ex: distance need to travel is 6, maximum waiting time is 90 - 6 = 84 Min.

5) If the value is negative in case 4, then we need to consider it for neutrals i.e, below 210 minutes.

	For ex: distance need to travel is 100, maximum waiting time is 90 - 100 = -10 Min.
	So, in the negative case we will consider the maximum wait time as travel distance 100 + 10 = 110 Min.

6) Also, giving a buffer time of 2 Minutes between two drone deliveries as we have single drone for whole town.

	
Implementation:
	
7) First we will sort the orders based on delivery time for the orders received before 6 AM.

8) Again sort the orders based on waiting time so that assumed total waiting time must not altered.

9) While one delivery is in progress, we check for new orders and perform 6 and 7 steps again.

10) Order time is checked to ensure no order are accepted after 10 PM.

11) NPS score is calculated based on the total time taken for each order and its corresponding rating (promotors, neutrals & detractors). 
