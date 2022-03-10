package tourGuide.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tourGuide.beans.AttractionBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.models.Reward;
import tourGuide.models.User;
import tourGuide.proxies.MicroserviceAttractionProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

	private final static Logger logger = LoggerFactory.getLogger(RewardService.class);
	private MicroserviceAttractionProxy microserviceAttractionProxy;
	
	public RewardService(MicroserviceAttractionProxy microserviceAttractionProxy) {
		this.microserviceAttractionProxy = microserviceAttractionProxy;
	}

	/**
	 * This method give reward points for each visitedLocations of a user only if it's in
	 * attraction proximity range.
	 * @param user
	 */

	public void calculateRewards(User user) {
		List<VisitedLocationBean> userLocations = user.getVisitedLocationBeans();
		List<AttractionBean> attractions = microserviceAttractionProxy.getAttractions();

		for(VisitedLocationBean visitedLocation : userLocations) {
			for(AttractionBean attraction : attractions) {
				if(microserviceAttractionProxy.isWithinAttractionProximity(attraction.getLatitude(), attraction.getLongitude(), visitedLocation.getLatitude(), visitedLocation.getLongitude())){
					Reward userReward = new Reward(visitedLocation, attraction, microserviceAttractionProxy.createAttractionRewardPoints());
					user.addUserReward(userReward);
					//logger.info("adding reward to user points");
				} else {
					//logger.info("No reward points added");
				}
			}
		}
	}

	public List<Reward> getUserRewards(User user) {
		return user.getUserRewards();
	}
}
