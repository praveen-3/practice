package org.food_ordering.models;

public record RestaurantRating(Restaurant restaurant, double rating) {
    public String getRestaurantId() {
        return restaurant.getRestaurantId();
    }
}