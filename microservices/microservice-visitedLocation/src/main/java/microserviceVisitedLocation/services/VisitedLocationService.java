package microserviceVisitedLocation.services;

import com.google.common.util.concurrent.RateLimiter;
import microserviceVisitedLocation.models.VisitedLocation;
import microserviceVisitedLocation.utilities.SleepUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class VisitedLocationService {

    private final static Logger logger = LoggerFactory.getLogger(VisitedLocationService.class);
    private static final RateLimiter rateLimiter = RateLimiter.create(10000);
    private final SleepUtilities sleepUtilities = new SleepUtilities();

    /**
     * Using a UUID userID, this method create a visitedLocation object
     * with random longitude and latitude.
     * @param userId
     * @return a visitedLocation object
     */

    public VisitedLocation createVisitedLocation(UUID userId) {
        rateLimiter.acquire();
        this.sleepUtilities.sleep();

        double longitude = ThreadLocalRandom.current().nextDouble(-180.0D, 180.0D);
        double latitude = ThreadLocalRandom.current().nextDouble(-85.05112878D, 85.05112878D);

        VisitedLocation visitedLocation = new VisitedLocation(userId, latitude, longitude, new Date());

        logger.info("Creating a new VisitedLocation");
        return visitedLocation;
    }
}
