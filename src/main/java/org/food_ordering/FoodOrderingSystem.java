package org.food_ordering;

import org.food_ordering.services.OrderService;
import org.food_ordering.services.RatingService;
import org.food_ordering.services.RestaurantService;
import org.food_ordering.models.Order;
import org.food_ordering.models.Rating;
import org.food_ordering.models.Restaurant;

import java.util.List;

import org.food_ordering.models.FoodItem;

public class FoodOrderingSystem {
    private final OrderService orderService;
    private final RatingService ratingService;
    private final RestaurantService restaurantService;

    public FoodOrderingSystem() {
        this.orderService = new OrderService();
        this.ratingService = new RatingService();
        this.restaurantService = new RestaurantService();
    }

    public void orderFood(String orderId, String restaurantId, String foodItemId) {
        // Validate that restaurant has this food item
        if (!restaurantService.validateOrder(restaurantId, foodItemId)) {
            // helper.printError("Invalid order: Restaurant " + restaurantId + 
            //                 " does not serve food item " + foodItemId);
            return;
        }

        Order order = new Order(orderId, restaurantId, foodItemId);
        orderService.createOrder(order);
        // helper.printSuccess("Order created successfully: " + orderId);
    }

    public void rateOrder(String orderId, int rating) {
        Order order = orderService.getOrder(orderId);
        if (order == null) {
            // helper.printError("Order not found: " + orderId);
            return;
        }

        Rating newRating = new Rating(rating);
        ratingService.addRating(order.restaurantId(), order.foodItemId(), newRating);
        // helper.printSuccess("Rating added successfully for order: " + orderId);
    }

    public List<Restaurant> getTopRestaurantsByFoodWithDetails(String foodItemId) {
        return restaurantService.getTopRestaurantsByFood(foodItemId, ratingService);
    }

    public List<Restaurant> getTopRatedRestaurantsWithDetails() {
        return restaurantService.getTopRatedRestaurants(ratingService);
    }

    // Additional methods to set up the system
    public void addRestaurant(String restaurantId, String name) {
        Restaurant restaurant = new Restaurant(restaurantId, name);
        restaurantService.addRestaurant(restaurant);
    }

    public void addFoodItem(String foodItemId, String name, boolean isVeg) {
        FoodItem foodItem = new FoodItem(foodItemId, name, isVeg);
        restaurantService.addFoodItem(foodItem);
    }

    public void addFoodItemToRestaurant(String restaurantId, String foodItemId) {
        restaurantService.addFoodItemToRestaurant(restaurantId, foodItemId);
    }
}