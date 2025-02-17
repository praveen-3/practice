package org.food_ordering.models;

public record Order(String orderId, String restaurantId, String foodItemId) {
}
