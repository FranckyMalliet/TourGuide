package microserviceTrip.models;

import java.util.UUID;

public class Trip {
    private final UUID tripId;
    private String name;
    private double price;

    public Trip(UUID tripId, String name, double price) {
        this.tripId = tripId;
        this.name = name;
        this.price = price;
    }

    public UUID getTripId() {
        return tripId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}