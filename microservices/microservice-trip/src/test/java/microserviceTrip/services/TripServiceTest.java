package microserviceTrip.services;

import microserviceTrip.models.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Test
    public void getTripPrices(){
        //GIVEN
        UUID attractionID = UUID.randomUUID();
        int adults = 2;
        int children = 1;
        int nightsStay = 3;
        int rewardPoints = 25;

        //WHEN
        List<Trip> tripList = tripService.getTripsPrices(attractionID,adults,children,nightsStay,rewardPoints);

        //THEN
        Assertions.assertNotNull(tripList);
        Assertions.assertEquals(tripList.size(), 5);
    }

    @Test
    public void getTripNameTest(){
        //GIVEN
        String tripName = tripService.getTripName();

        //THEN
        Assertions.assertNotNull(tripName);
    }
}
