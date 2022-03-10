package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tourGuide.beans.AttractionBean;
import tourGuide.feign.FeignConfig;

import java.util.List;

@FeignClient(name= "microserviceAttraction", url = "localhost:9001", configuration = FeignConfig.class)
public interface MicroserviceAttractionProxy {

    @GetMapping(value = "/createAttractions")
    List<AttractionBean> getAttractions();

    @GetMapping(value = "/fiveClosestTouristAttractions")
    List<List<String>> fiveClosestTouristAttractions(@RequestParam double latitude,
                                                     @RequestParam double longitude);

    @GetMapping(value = "/createAttractionRewardPoints")
    int createAttractionRewardPoints();

    @GetMapping(value = "/isWithinAttractionProximity")
    boolean isWithinAttractionProximity(@RequestParam double attractionLatitude,
                                               @RequestParam double attractionLongitude,
                                               @RequestParam double locationLatitude,
                                               @RequestParam double locationLongitude);

    @GetMapping(value = "/newProximityBuffer")
    void newProximityBuffer(int proximityBuffer);

    @GetMapping(value = "/defaultProximityBuffer")
    void defaultProximityBuffer();
}
