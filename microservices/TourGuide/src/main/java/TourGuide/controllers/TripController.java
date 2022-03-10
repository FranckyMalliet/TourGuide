package tourGuide.controllers;

import com.jsoniter.output.JsonStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.beans.TripBean;
import tourGuide.helper.InternalTestHelper;
import tourGuide.services.TripService;

import java.util.List;

@RestController
public class TripController {

    private TripService tripService;
    private InternalTestHelper internalTestHelper;

    public void TripController(TripService tripService, InternalTestHelper internalTestHelper){
        this.tripService = tripService;
        this.internalTestHelper = internalTestHelper;
    }

    @RequestMapping("/getTripDeals")
    public String getTripDeals(@RequestParam String userName) {
        List<TripBean> tripPrices = tripService.getTripDeals(internalTestHelper.getUser(userName));
        return JsonStream.serialize(tripPrices);
    }
}
