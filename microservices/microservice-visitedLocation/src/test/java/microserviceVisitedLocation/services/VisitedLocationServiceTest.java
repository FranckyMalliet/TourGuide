package microserviceVisitedLocation.services;

import microserviceVisitedLocation.models.VisitedLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class VisitedLocationServiceTest {

    @Autowired
    private VisitedLocationService visitedLocationService;
    
    @Test
    public void createVisitedLocationTest() {
        //GIVEN
        UUID userID = UUID.randomUUID();
        
        //WHEN
        VisitedLocation visitedLocation = visitedLocationService.createVisitedLocation(userID);
        
        //
        Assertions.assertNotNull(visitedLocation);
        Assertions.assertNotNull(visitedLocation.getLatitude());
        Assertions.assertNotNull(visitedLocation.getLongitude());
        Assertions.assertEquals(userID, visitedLocation.getUserId());
    }
}
