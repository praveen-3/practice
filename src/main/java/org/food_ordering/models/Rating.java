package org.food_ordering.models;

public record Rating(int value) {
    public Rating {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }
}
