package tourGuide.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.beans.TripBean;
import tourGuide.helper.InternalTestHelper;
import tourGuide.models.User;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Test
    public void getTripDeals() {
        InternalTestHelper.setInternalUserNumber(0);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        List<TripBean> tripDeals = tripService.getTripDeals(user);

        Assertions.assertNotNull(tripDeals);
        Assertions.assertTrue(tripDeals.size() > 0);
    }
}
