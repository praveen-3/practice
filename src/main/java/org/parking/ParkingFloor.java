package org.parking;

public class ParkingFloor {
    private final int floorNumber;
    private final ParkingSpot[][] spots;

    public ParkingFloor(int floorNumber, String[][] floorData) {
        this.floorNumber = floorNumber;
        this.spots = new ParkingSpot[floorData.length][floorData[0].length];
        initializeSpots(floorData);
    }

    private void initializeSpots(String[][] floorData) {
        for (int i = 0; i < floorData.length; i++) {
            for (int j = 0; j < floorData[i].length; j++) {
                String spotData = floorData[i][j];
                VehicleType type = VehicleType.fromValue(Character.getNumericValue(spotData.charAt(0)));
                boolean isActive = spotData.charAt(2) == '1';
                String spotId = String.format("%d-%d-%d", floorNumber, i, j);
                spots[i][j] = new ParkingSpot(spotId, type, isActive);
            }
        }
    }

    public int getFreeSpotsCount(VehicleType type) {
        int count = 0;
        for (ParkingSpot[] row : spots) {
            for (ParkingSpot spot : row) {
                if (spot.getType() == type && spot.isAvailable()) {
                    count++;
                }
            }
        }
        return count;
    }

    public ParkingSpot findAvailableSpot(VehicleType type) {
        for (ParkingSpot[] row : spots) {
            for (ParkingSpot spot : row) {
                if (spot.getType() == type && spot.isAvailable()) {
                    return spot;
                }
            }
        }
        return null;
    }
}
