package tourGuide.proxies;

import org.springframework.web.bind.annotation.RequestParam;
import tourGuide.beans.TripBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tourGuide.feign.FeignConfig;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "microserviceTrip", url = "localhost:9002", configuration = FeignConfig.class)
public interface MicroserviceTripProxy {

    @GetMapping(value = "/getTripName")
    String getTripName();

    @GetMapping(value = "/getTripsPrices")
    public List<TripBean> getTripsPrices(@RequestParam UUID attractionId,
                                     @RequestParam int adults,
                                     @RequestParam int children,
                                     @RequestParam int nightsStay,
                                     @RequestParam int rewardsPoints);
}
