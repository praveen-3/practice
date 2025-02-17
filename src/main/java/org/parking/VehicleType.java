package org.parking;

public enum VehicleType {
    TWO_WHEELER(2),
    FOUR_WHEELER(4);

    private final int value;

    VehicleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static VehicleType fromValue(int value) {
        for (VehicleType type : values()) {
            if (type.value == value) return type;
        }
        throw new IllegalArgumentException("Invalid vehicle type: " + value);
    }
}
