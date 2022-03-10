package tourGuide;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tourGuide.beans.AttractionBean;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.helper.InternalTestHelper;
import tourGuide.models.User;
import tourGuide.proxies.MicroserviceAttractionProxy;
import tourGuide.services.RewardService;
import tourGuide.services.VisitedLocationService;
import tourGuide.tracker.Tracker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTest {

	private final static Logger logger = LoggerFactory.getLogger(PerformanceTest.class);

	@Autowired
	private MicroserviceAttractionProxy microserviceAttractionProxy;

	@Autowired
	private VisitedLocationService visitedLocationService;

	@Autowired
	private RewardService rewardService;

	@Autowired
	private InternalTestHelper internalTestHelper;

	private Tracker tracker = new Tracker(visitedLocationService, internalTestHelper);

	/*
	 * A note on performance improvements:
	 *     
	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
	 *     
	 *     		InternalTestHelper.setInternalUserNumber(100000);
	 *     
	 *     
	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
	 *     at the end of the tests remains consistent. 
	 * 
	 *     These are performance metrics that we are trying to hit:
	 *     
	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	 */

	@Test
	public void highVolumeTrackLocation() {
		logger.info("TestMode enabled");
		logger.debug("Initializing users");

		// Users should be incremented up to 100,000, and test finishes within 15 minutes
		InternalTestHelper.setInternalUserNumber(100);
		internalTestHelper.initializeInternalUsers();
		logger.debug("Finished initializing users");

		List<User> allUsers = internalTestHelper.getAllUsers();
		tracker = new Tracker(visitedLocationService, internalTestHelper);
		Runtime.getRuntime().addShutdownHook(new Thread(tracker::stopTracking));

	    StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for(User user : allUsers) {
			visitedLocationService.createVisitedLocationAndCalculateUserReward(user);
		}
		stopWatch.stop();
		tracker.stopTracking();

		System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
		Assertions.assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

	@Test
	public void highVolumeGetRewards() {
		logger.info("TestMode enabled");
		logger.debug("Initializing users");

		// Users should be incremented up to 100,000, and test finishes within 20 minutes
		InternalTestHelper.setInternalUserNumber(100);
		internalTestHelper.initializeInternalUsers();
		logger.debug("Finished initializing users");

		List<User> allUsers =  internalTestHelper.getAllUsers();
		tracker = new Tracker(visitedLocationService, internalTestHelper);
		Runtime.getRuntime().addShutdownHook(new Thread(tracker::stopTracking));

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
	    AttractionBean attraction = microserviceAttractionProxy.getAttractions().get(0);

		allUsers.forEach(user -> user.addToVisitedLocations(new VisitedLocationBean(user.getUserId(), attraction.getLatitude(), attraction.getLongitude(), new Date())));
	    allUsers.forEach(user -> rewardService.calculateRewards(user));

		for(User user : allUsers) {
			Assertions.assertTrue(user.getUserRewards().size() > 0);
			System.out.println(user.getUserRewards());
		}
		stopWatch.stop();
		tracker.stopTracking();

		System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		Assertions.assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}
}
