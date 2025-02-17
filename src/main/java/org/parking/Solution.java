package org.parking;

public class Solution {
    private final ParkingLotService parkingLotService;

    public Solution() {
        String[][][] parking = new String[][][] {
                {
                        {"4-1","4-1","2-1","2-0"},
                        {"2-1","4-1","2-1","2-1"},
                        {"4-0","2-1","4-0","2-1"},
                        {"4-1","4-1","4-1","2-1"}
                }
        };
        this.parkingLotService = new ParkingLotService(parking);
    }

    public ParkingResult park(int vehicleType, String vehicleNumber, String ticketId) {
        return parkingLotService.park(vehicleType, vehicleNumber, ticketId);
    }

    public int removeVehicle(String spotId, String vehicleNumber, String ticketId) {
        return parkingLotService.removeVehicle(spotId, vehicleNumber, ticketId);
    }

    public ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId) {
        return parkingLotService.searchVehicle(spotId, vehicleNumber, ticketId);
    }

    public int getFreeSpotsCount(int floor, int vehicleType) {
        return parkingLotService.getFreeSpotsCount(floor, vehicleType);
    }
}
