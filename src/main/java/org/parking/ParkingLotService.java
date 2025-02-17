package org.parking;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class ParkingLotService {
    private final List<ParkingFloor> floors;
    private final Map<String, ParkingSpot> spotIdMap;
    private final Map<String, ParkingSpot> vehicleNumberMap;
    private final Map<String, ParkingSpot> ticketIdMap;

    public ParkingLotService(String[][][] parkingData) {
        this.floors = new ArrayList<>();
        this.spotIdMap = new ConcurrentHashMap<>();
        this.vehicleNumberMap = new ConcurrentHashMap<>();
        this.ticketIdMap = new ConcurrentHashMap<>();
        initializeFloors(parkingData);
    }

    private void initializeFloors(String[][][] parkingData) {
        for (int i = 0; i < parkingData.length; i++) {
            floors.add(new ParkingFloor(i, parkingData[i]));
        }
    }

    public ParkingResult park(int vehicleType, String vehicleNumber, String ticketId) {
        Vehicle vehicle = new Vehicle(vehicleNumber, VehicleType.fromValue(vehicleType));
        
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.type());
            if (spot != null && spot.park(vehicle, ticketId)) {
                // Update maps atomically
                spotIdMap.put(spot.getSpotId(), spot);
                vehicleNumberMap.put(vehicleNumber, spot);
                ticketIdMap.put(ticketId, spot);
                
                return new ParkingResult(201, spot.getSpotId(), vehicleNumber, ticketId);
            }
        }
        return new ParkingResult(404, "", vehicleNumber, ticketId);
    }

    public int removeVehicle(String spotId, String vehicleNumber, String ticketId) {
        ParkingSpot spot = findSpot(spotId, vehicleNumber, ticketId);
        if (spot != null && spot.unpark()) {
            // Remove from maps atomically
            spotIdMap.remove(spot.getSpotId());
            vehicleNumberMap.remove(spot.getParkedVehicle().vehicleNumber());
            ticketIdMap.remove(spot.getTicketId());
            return 201;
        }
        return 404;
    }

    public ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId) {
        ParkingSpot spot = findSpot(spotId, vehicleNumber, ticketId);
        if (spot != null) {
            return new ParkingResult(201, spot.getSpotId(), spot.getParkedVehicle().vehicleNumber(), spot.getTicketId());
        }
        return new ParkingResult(404, "", "", "");
    }

    public int getFreeSpotsCount(int floor, int vehicleType) {
        if (floor >= 0 && floor < floors.size()) {
            return floors.get(floor).getFreeSpotsCount(VehicleType.fromValue(vehicleType));
        }
        return 0;
    }

    private ParkingSpot findSpot(String spotId, String vehicleNumber, String ticketId) {
        if (!spotId.isEmpty()) return spotIdMap.get(spotId);
        if (!vehicleNumber.isEmpty()) return vehicleNumberMap.get(vehicleNumber);
        if (!ticketId.isEmpty()) return ticketIdMap.get(ticketId);
        return null;
    }
}
