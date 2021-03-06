package tourGuide.beans;

import java.util.UUID;

public class TripBean {
    private final UUID tripId;
    private String name;
    private double price;

    public TripBean(UUID tripId, String name, double price) {
        this.name = name;
        this.tripId = tripId;
        this.price = price;
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

    public UUID getTripId() {
        return tripId;
    }
}
