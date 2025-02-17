package org.food_ordering.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
    
public class Restaurant {
    private final String restaurantId;
    private final String name;
    private final Set<String> foodItems; // foodItemIds offered by restaurant
    private double currentRating;

    public Restaurant(String restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.foodItems = new HashSet<>();
        this.currentRating = 0.0;
    }

    public void addFoodItem(String foodItemId) {
        if (foodItems.size() >= 25) { // Max 25 food items per restaurant
            throw new IllegalStateException("Restaurant cannot have more than 25 food items");
        }
        foodItems.add(foodItemId);
    }

    public boolean hasFoodItem(String foodItemId) {
        return foodItems.contains(foodItemId);
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public Set<String> getFoodItems() {
        return Collections.unmodifiableSet(foodItems);
    }

    public double getCurrentRating() {
        return currentRating;
    }

    void setCurrentRating(double rating) {
        this.currentRating = rating;
    }

    @Override
    public String toString() {
        return String.format("Restaurant{id='%s', name='%s', rating=%.1f, foodItems=%d}",
                restaurantId, name, currentRating, foodItems.size());
    }
}