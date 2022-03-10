package tourGuide.services;

import tourGuide.beans.TripBean;
import tourGuide.models.Reward;
import tourGuide.models.User;
import tourGuide.proxies.MicroserviceTripProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    private MicroserviceTripProxy microserviceTripProxy;

    public TripService(MicroserviceTripProxy microserviceTripProxy) {
        this.microserviceTripProxy = microserviceTripProxy;
    }

    /**
     * This method calculate the amount of reward points of a user,
     * after that, it will gather trip prices and the user preferences
     * into a list.
     * @param user
     * @return a list of trip objects
     */

    public List<TripBean> getTripDeals(User user) {
        int cumulativeRewardPoints = user.getUserRewards().stream().mapToInt(Reward::getRewardPoints).sum();

        List<TripBean> tripList = microserviceTripProxy.getTripsPrices(
                user.getUserId(),
                user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(),
                user.getUserPreferences().getTripDuration(), cumulativeRewardPoints);
        user.setTripDeals(tripList);

        return tripList;
    }
}
