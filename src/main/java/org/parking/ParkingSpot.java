package org.parking;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParkingSpot {
    private final String spotId;
    private final VehicleType type;
    private final boolean isActive;
    private Vehicle parkedVehicle;
    private String ticketId;
    private final ReentrantReadWriteLock lock;

    public ParkingSpot(String spotId, VehicleType type, boolean isActive) {
        this.spotId = spotId;
        this.type = type;
        this.isActive = isActive;
        this.lock = new ReentrantReadWriteLock();
    }

    public boolean park(Vehicle vehicle, String ticketId) {
        lock.writeLock().lock();
        try {
            if (!isActive || parkedVehicle != null || vehicle.type() != type) {
                return false;
            }
            this.parkedVehicle = vehicle;
            this.ticketId = ticketId;
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean unpark() {
        lock.writeLock().lock();
        try {
            if (parkedVehicle == null) {
                return false;
            }
            this.parkedVehicle = null;
            this.ticketId = null;
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Getters with read locks
    public String getSpotId() {
        return spotId;
    }

    public boolean isAvailable() {
        lock.readLock().lock();
        try {
            return isActive && parkedVehicle == null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public String getTicketId() {
        return ticketId;
    }

    public VehicleType getType() {
        return type;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
