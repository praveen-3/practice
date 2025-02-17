package org.parking;

public record ParkingResult(int status, String spotId, String vehicleNumber, String ticketId) {
}