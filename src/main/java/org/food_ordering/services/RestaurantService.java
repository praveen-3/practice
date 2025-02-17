package org.food_ordering.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.food_ordering.models.FoodItem;
import org.food_ordering.models.Restaurant;
import org.food_ordering.models.RestaurantRating;

public class RestaurantService {
    private final Map<String, Restaurant> restaurants;
    private final Map<String, FoodItem> foodItems;

    public RestaurantService() {
        this.restaurants = new HashMap<>();
        this.foodItems = new HashMap<>();
    }

    public void addRestaurant(Restaurant restaurant) {
        if (restaurants.size() >= 10000) { // Max 10000 restaurants
            throw new IllegalStateException("Cannot add more than 10000 restaurants");
        }
        restaurants.put(restaurant.getRestaurantId(), restaurant);
    }

    public void addFoodItem(FoodItem foodItem) {
        if (foodItems.size() >= 50) { // Max 50 food items
            throw new IllegalStateException("Cannot add more than 50 food items");
        }
        foodItems.put(foodItem.foodItemId(), foodItem);
    }

    public void addFoodItemToRestaurant(String restaurantId, String foodItemId) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }
        if (!foodItems.containsKey(foodItemId)) {
            throw new IllegalArgumentException("Food item not found");
        }
        restaurant.addFoodItem(foodItemId);
    }

    public boolean validateOrder(String restaurantId, String foodItemId) {
        Restaurant restaurant = restaurants.get(restaurantId);
        return restaurant != null && restaurant.hasFoodItem(foodItemId);
    }

    public List<Restaurant> getTopRestaurantsByFood(String foodItemId, RatingService ratingService) {
        if (!foodItems.containsKey(foodItemId)) {
            return Collections.emptyList();
        }

        return restaurants.values().stream()
                .filter(restaurant -> restaurant.hasFoodItem(foodItemId))
                .map(restaurant -> new RestaurantRating(
                    restaurant,
                    ratingService.getAverageRatingForFood(restaurant.getRestaurantId(), foodItemId)
                ))
                .filter(rr -> rr.rating() > 0)
                .sorted((rr1, rr2) -> {
                    int ratingCompare = Double.compare(rr2.rating(), rr1.rating());
                    return ratingCompare != 0 ? ratingCompare :
                           rr1.getRestaurantId().compareTo(rr2.getRestaurantId());
                })
                .limit(20)
                .map(RestaurantRating::restaurant)
                .collect(Collectors.toList());
    }

    public List<Restaurant> getTopRatedRestaurants(RatingService ratingService) {

        return restaurants.values().stream()
                .map(restaurant -> new RestaurantRating(
                    restaurant,
                    ratingService.getAverageRatingForRestaurant(restaurant.getRestaurantId())
                ))
                .filter(rr -> rr.rating() > 0)
                .sorted((rr1, rr2) -> {
                    int ratingCompare = Double.compare(rr2.rating(), rr1.rating());
                    return ratingCompare != 0 ? ratingCompare : 
                           rr1.getRestaurantId().compareTo(rr2.getRestaurantId());
                })
                .limit(20)
                .map(RestaurantRating::restaurant)
                .collect(Collectors.toList());
    }
}
