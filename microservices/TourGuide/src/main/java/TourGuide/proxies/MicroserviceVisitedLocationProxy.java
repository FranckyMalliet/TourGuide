package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.feign.FeignConfig;

import java.util.UUID;

@FeignClient(name = "microserviceVisitedLocation", url = "localhost:9004", configuration = FeignConfig.class)
public interface MicroserviceVisitedLocationProxy {

    @GetMapping(value = "/createVisitedLocation")
    VisitedLocationBean createVisitedLocation(@RequestParam("userId") UUID userId);
}
