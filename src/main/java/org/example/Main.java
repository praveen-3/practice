package org.example;

import org.parking.ParkingResult;
import org.parking.Solution;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int result = solution.getFreeSpotsCount(0,2);
        System.out.println("free spots: " + result);

        ParkingResult parkingResult = solution.park(2, "1991", "1");
        if(parkingResult.status() == 201) {
            System.out.println("Parked at: " + parkingResult.spotId());
        } else {
            System.out.println("couldn't be parked");
        }
        result = solution.getFreeSpotsCount(0,2);
        System.out.println("free spots: " + result);

        solution.park(2, "1991", "1");
        solution.park(2, "1992", "2");
        solution.park(2, "1993", "3");
        solution.park(2, "1994", "4");
        solution.park(2, "1995", "5");
        solution.park(2, "1996", "6");
        solution.park(2, "1997", "7");
        solution.park(2, "1998", "8");
        parkingResult = solution.park(2, "1999", "9");
        if(parkingResult.status() == 201) {
            System.out.println("Parked at: " + parkingResult.spotId());
        } else {
            System.out.println("couldn't be parked");
        }
    }
}