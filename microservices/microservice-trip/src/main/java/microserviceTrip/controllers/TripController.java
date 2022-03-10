package microserviceTrip.controllers;

import microserviceTrip.models.Trip;
import microserviceTrip.services.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TripController {

    private final static Logger logger = LoggerFactory.getLogger(TripController.class);
    private TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @GetMapping(value = "/getTripName")
    public String getTripName(){
        //logger.info("Getting new TripName");
        return tripService.getTripName();
    }

    @GetMapping(value = "/getTripsPrices")
    public List<Trip> getTripsPrices(@RequestParam UUID attractionId,
                                     @RequestParam int adults,
                                     @RequestParam int children,
                                     @RequestParam int nightsStay,
                                     @RequestParam int rewardsPoints){
        //logger.info("Getting Trip Prices : " + attractionId + " " + adults + " " + children + " " + nightsStay + " " + rewardsPoints);
        return tripService.getTripsPrices(attractionId, adults, children, nightsStay, rewardsPoints);
    }
}
