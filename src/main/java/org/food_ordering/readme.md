requirements:
- add restaurant
- add food item to restaurant
- order food
- rate order
- get top restaurants by food
- get top-rated restaurants

design:
- use a map to store restaurants
- use a map to store food items
- use a map to store orders
- use a map to store ratings
- use a map to store restaurant ratings

uml:

```mermaid
classDiagram
    class Restaurant {
        +String id
        +String name
        +List<FoodItem> foodItems
        +double rating
        +addFoodItem(FoodItem item)
        +updateRating(double rating)
    }   
    
    class FoodItem {
        +String id
        +String name
        +double price
        +boolean isVeg
    }   
    
    class Order {
        +String id
        +String restaurantId
        +String foodItemId
        +OrderStatus status
        +createOrder()
        +updateStatus(OrderStatus status)
    }   
    
    class Rating {
        +String id
        +String orderId
        +double rating
        +String comment
        +submitRating()
    }   
    
    class FoodOrderingSystem {
        -RestaurantService restaurantService
        -OrderService orderService
        -RatingService ratingService
        +addRestaurant(Restaurant restaurant)
        +placeOrder(Order order)
        +rateOrder(Rating rating)
        +getTopRestaurants()
        +getRestaurantsByFood(String foodName)
    }   

    class RestaurantService {
        -Map<String, Restaurant> restaurants
        -Map<String, FoodItem> foodItems
        +addRestaurant(Restaurant restaurant)
        +addFoodItem(String restaurantId, FoodItem item)
        +getRestaurant(String id)
        +getTopRatedRestaurants()
        +getRestaurantsByFood(String foodName)
    }

    class OrderService {
        -Map<String, Order> orders
        +createOrder(Order order)
        +getOrder(String id)
        +updateOrderStatus(String id, OrderStatus status)
    }

    class RatingService {
        -Map<String, Rating> ratings
        +submitRating(Rating rating)
        +getAverageRating(String restaurantId)
        +getRatingsForRestaurant(String restaurantId)
    }
    
    class OrderStatus {
        <<enumeration>>
        PLACED
        ACCEPTED
        PREPARING
        READY
        DELIVERED
        CANCELLED
    }

    Restaurant "1" *-- "*" FoodItem
    Order "*" --> "1" Restaurant
    Order "*" --> "1" FoodItem
    Rating "1" --> "1" Order
    
    FoodOrderingSystem "1" *-- "1" RestaurantService
    FoodOrderingSystem "1" *-- "1" OrderService
    FoodOrderingSystem "1" *-- "1" RatingService
    
    RestaurantService "1" --> "*" Restaurant
    OrderService "1" --> "*" Order
    RatingService "1" --> "*" Rating
    Order --> OrderStatus
```
