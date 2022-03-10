package tourGuide.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.helper.InternalTestHelper;
import tourGuide.models.User;

import java.util.*;

@SpringBootTest
public class VisitedLocationServiceTest {

    @Autowired
    private VisitedLocationService visitedLocationService;

    @Autowired
    private InternalTestHelper internalTestHelper;

    @Test
    public void getUserLastVisitedLocationTest() {
        //GIVEN
        User user = new User(UUID.randomUUID(), "Johnson", "0645656565", "Shadowrun@Cyberpunk.com");

        //WHEN
        VisitedLocationBean visitedLocation = visitedLocationService.getUserLastVisitedLocation(user);

        //THEN
        Assertions.assertNotNull(visitedLocation);
    }

    @Test
    public void createVisitedLocationAndCalculateUserRewardTest() {
        //GIVEN
        User user = new User(UUID.randomUUID(), "Johnson", "0645656565", "Shadowrun@Cyberpunk.com");

        //WHEN
        VisitedLocationBean visitedLocation = visitedLocationService.createVisitedLocationAndCalculateUserReward(user);

        //THEN
        Assertions.assertNotNull(visitedLocation);;
    }

    @Test
    public void findAllMostRecentUsersVisitedLocationsTest() {
        //GIVEN
        User user = new User(UUID.randomUUID(), "Sauron", "0645656565", "Mordor@middleEarth.com");
        user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), 33, -116, new Date()));
        user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), 44, -111, new Date()));
        user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), 36, -92, new Date()));

        List<User> userList = new ArrayList<>();


        //WHEN
        Map<String, List<Double>> usersMostRecentLocations = visitedLocationService.findAllMostRecentUsersVisitedLocations(userList);

        //THEN
        Assertions.assertNotNull(usersMostRecentLocations);
    }
}
