package tourGuide.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.models.Reward;
import tourGuide.models.User;
import tourGuide.proxies.MicroserviceVisitedLocationProxy;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class RewardServiceTest {

	@Autowired
	private RewardService rewardService;

	@Autowired
	private VisitedLocationService visitedLocationService;

	@Autowired
	private MicroserviceVisitedLocationProxy microserviceVisitedLocationProxy;

	@Test
	public void calculateRewardsTest(){
		//GIVEN
		User user = new User(UUID.randomUUID(), "Harkonnen", "0645656565", "Gebprime@hotmail.com");

		user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), 33, -116, new Date()));
		user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), 44, -111, new Date()));
		user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), 36, -92, new Date()));

		int rewardPoints = 0;

		//WHEN
		rewardService.calculateRewards(user);
		for(Reward reward : user.getUserRewards()){
			rewardPoints += reward.getRewardPoints();
		}

		//THEN
		Assertions.assertTrue(rewardPoints > 0);
	}
}
