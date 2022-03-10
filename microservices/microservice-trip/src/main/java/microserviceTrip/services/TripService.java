package microserviceTrip.services;

import microserviceTrip.models.Trip;
import microserviceTrip.utilities.SleepUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TripService {

    private final static Logger logger = LoggerFactory.getLogger(TripService.class);
    private final SleepUtilities sleepUtilities = new SleepUtilities();

    /**
     * Using the user preferences, return 5 trips with their names and prices
     * @param userId
     * @param adults
     * @param children
     * @param nightsStay
     * @param rewardsPoints
     * @return a list of trips with prices
     */

    public List<Trip> getTripsPrices(UUID userId, int adults, int children, int nightsStay, int rewardsPoints){
        List<Trip> tripList = new ArrayList<>();

        sleepUtilities.sleep();

        for(int i = 0; i < 5; i++){
            double tripPrice = createPrice(adults, children, nightsStay, rewardsPoints);
            String tripName = getTripName();
            tripList.add(new Trip(userId, tripName, tripPrice));
            logger.info("Adding new trip : " + tripName + " " + tripPrice);
        }

        return tripList;
    }

    /**
     * This method generate a random name
     * @return a random name for a trip
     */

    public String getTripName() {
        int multiple = ThreadLocalRandom.current().nextInt(1, 10);
        switch(multiple) {
            case 1:
                return "Holiday Travels";
            case 2:
                return "Enterprise Ventures Limited";
            case 3:
                return "Sunny Days";
            case 4:
                return "FlyAway Trips";
            case 5:
                return "United Partners Vacations";
            case 6:
                return "Dream Trips";
            case 7:
                return "Live Free";
            case 8:
                return "Dancing Waves Cruse lines and Partners";
            case 9:
                return "AdventureCo";
            default:
                return "Cure-Your-Blues";
        }
    }

    /**
     * Using the user preferences, generate a price
     * @param adults
     * @param children
     * @param nightsStay
     * @param rewardsPoints
     * @return a (double) price
     */

    private double createPrice(int adults, int children, int nightsStay, int rewardsPoints){
        int multiple = ThreadLocalRandom.current().nextInt(100, 700);
        double childrenDiscount = children / 3;
        double price = (double)(multiple * adults) + (double)multiple * childrenDiscount * (double)nightsStay + 0.99 - (double)rewardsPoints;
        if (price < 0.0) {
            price = 0.0;
        }
        return price;
    }
}