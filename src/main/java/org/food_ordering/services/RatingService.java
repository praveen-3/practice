package org.food_ordering.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.food_ordering.models.Rating;

public class RatingService {
    // Map<RestaurantId, Map<FoodItemId, List<Rating>>>
    private final Map<String, Map<String, List<Rating>>> ratings;
    
    public RatingService() {
        this.ratings = new HashMap<>();
    }

    public void addRating(String restaurantId, String foodItemId, Rating rating) {
        ratings.computeIfAbsent(restaurantId, k -> new HashMap<>())
               .computeIfAbsent(foodItemId, k -> new ArrayList<>())
               .add(rating);
    }

    public double getAverageRatingForRestaurant(String restaurantId) {
        if (!ratings.containsKey(restaurantId)) return 0.0;
        
        List<Rating> allRatings = ratings.get(restaurantId).values().stream()
                .flatMap(List::stream)
                .toList();
                
        if (allRatings.isEmpty()) return 0.0;
        
        double avg = allRatings.stream()
                .mapToInt(Rating::value)
                .average()
                .orElse(0.0);
                
        return roundRating(avg);
    }

    public double getAverageRatingForFood(String restaurantId, String foodItemId) {
        if (!ratings.containsKey(restaurantId) || 
            !ratings.get(restaurantId).containsKey(foodItemId)) {
            return 0.0;
        }
        
        List<Rating> foodRatings = ratings.get(restaurantId).get(foodItemId);
        if (foodRatings.isEmpty()) return 0.0;
        
        double avg = foodRatings.stream()
                .mapToInt(Rating::value)
                .average()
                .orElse(0.0);
                
        return roundRating(avg);
    }

    private double roundRating(double rating) {
        return (double)((int)((rating + 0.05) * 10)) / 10.0;
    }
}
