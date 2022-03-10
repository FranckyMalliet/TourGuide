package tourGuide.models;

import tourGuide.beans.TripBean;
import tourGuide.beans.VisitedLocationBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private String phoneNumber;
    private String emailAddress;
    private Date latestLocationTimestamp;
    private UserPreferences userPreferences = new UserPreferences();
    private List<VisitedLocationBean> visitedLocationBeans = new ArrayList();
    private List<Reward> userRewards = new ArrayList();
    private List<TripBean> tripDeals = new ArrayList();

    public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLatestLocationTimestamp() {
        return latestLocationTimestamp;
    }

    public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
        this.latestLocationTimestamp = latestLocationTimestamp;
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void setVisitedLocationBeans(List<VisitedLocationBean> visitedLocationBeans) {
        this.visitedLocationBeans = visitedLocationBeans;
    }

    public void addUserReward(Reward userReward) {
        if ((long) this.userRewards.size() == 0L) {
            this.userRewards.add(userReward);
        }
    }

    public List<Reward> getUserRewards() {
        return this.userRewards;
    }

    public void setUserRewards(List<Reward> userRewards) {
        this.userRewards = userRewards;
    }

    public void addToVisitedLocations(VisitedLocationBean visitedLocationBean) {
        this.visitedLocationBeans.add(visitedLocationBean);
    }

    public List<VisitedLocationBean> getVisitedLocationBeans() {
        return this.visitedLocationBeans;
    }

    public void clearVisitedLocations() {
        this.visitedLocationBeans.clear();
    }

    public VisitedLocationBean getLastVisitedLocation() {
        return (VisitedLocationBean)this.visitedLocationBeans.get(this.visitedLocationBeans.size() - 1);
    }

    public void setTripDeals(List<TripBean> tripDeals) {
        this.tripDeals = tripDeals;
    }

    public List<TripBean> getTripDeals() {
        return this.tripDeals;
    }
}
