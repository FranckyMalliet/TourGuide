package microserviceVisitedLocation.models;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class VisitedLocation {
    private UUID userId;
    private Date timeVisited;
    private double latitude;
    private double longitude;

    public VisitedLocation(UUID userId, double latitude, double longitude, Date timeVisited) {
        Objects.requireNonNull(longitude);
        Objects.requireNonNull(latitude);
        Objects.requireNonNull(timeVisited);

        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeVisited = timeVisited;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getTimeVisited() {
        return timeVisited;
    }

    public void setTimeVisited(Date timeVisited) {
        this.timeVisited = timeVisited;
    }
}