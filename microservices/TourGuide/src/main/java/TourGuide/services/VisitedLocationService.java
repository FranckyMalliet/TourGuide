package tourGuide.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.helper.InternalTestHelper;
import tourGuide.models.User;
import tourGuide.proxies.MicroserviceVisitedLocationProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisitedLocationService {

    private final static Logger logger = LoggerFactory.getLogger(VisitedLocationService.class);
    private RewardService rewardService;
    private MicroserviceVisitedLocationProxy microserviceVisitedLocationProxy;

    public VisitedLocationService(RewardService rewardService, MicroserviceVisitedLocationProxy microserviceVisitedLocationProxy, InternalTestHelper internalTestHelper){
        this.rewardService = rewardService;
        this.microserviceVisitedLocationProxy = microserviceVisitedLocationProxy;
    }

    /**
     * This method use a list of visitedLocation from a user and retrieve the last visitedLocation.
     * Then, create a visitedLocation and calculate a reward.
     * @param user
     * @return a visitedLocation object
     */

    public VisitedLocationBean getUserLastVisitedLocation(User user) {
        VisitedLocationBean visitedLocation = (user.getVisitedLocationBeans().size() > 0) ?
                user.getLastVisitedLocation() :
                createVisitedLocationAndCalculateUserReward(user);

        //logger.info("Retrieve the last visitedLocation" + visitedLocation.getUserId() + " from user : " + user.getUserId());
        return visitedLocation;
    }

    /**
     * This method create a visitedLocation using a userId and calculate the reward points of a user.
     * @param user
     * @return a visitedLocation object
     */

    public VisitedLocationBean createVisitedLocationAndCalculateUserReward(User user) {
        VisitedLocationBean visitedLocation = microserviceVisitedLocationProxy.createVisitedLocation(user.getUserId());
        user.addToVisitedLocations(visitedLocation);
        rewardService.calculateRewards(user);

        //logger.info("Create visitedLocation with user id number : " + visitedLocation.getUserId() + ". Calculate reward for the user");
        return visitedLocation;
    }

    /**
     * This method use a list of user and add the last visitedLocation latitude
     * and longitude of each user into the map.
     * @return a map list of double values
     */

    public Map<String, List<Double>> findAllMostRecentUsersVisitedLocations(List<User> userList){
        Map<String, List<Double>> usersMostRecentLocations = new HashMap<>();

        for (User user : userList){
            String userId = String.valueOf(user.getUserId());

            double visitedLocationLatitude = user.getLastVisitedLocation().getLatitude();
            double visitedLocationLongitude = user.getLastVisitedLocation().getLongitude();

            List<Double> visitedLocationLatitudeAndLongitude = new ArrayList<>();
            visitedLocationLatitudeAndLongitude.add(visitedLocationLatitude);
            visitedLocationLatitudeAndLongitude.add(visitedLocationLongitude);

            usersMostRecentLocations.put(userId, visitedLocationLatitudeAndLongitude);
        }

        return usersMostRecentLocations;
    }
}