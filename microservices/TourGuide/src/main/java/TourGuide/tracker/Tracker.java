package tourGuide.tracker;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tourGuide.helper.InternalTestHelper;
import tourGuide.models.User;
import tourGuide.services.VisitedLocationService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tracker extends Thread {

	private final static Logger logger = LoggerFactory.getLogger(Tracker.class);
	private static final long trackingPollingInterval = TimeUnit.MINUTES.toSeconds(5);
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();
	private VisitedLocationService visitedLocationService;
	private InternalTestHelper internalTestHelper;

	private boolean stop = false;

	public Tracker(VisitedLocationService visitedLocationService, InternalTestHelper internalTestHelper) {
		this.visitedLocationService = visitedLocationService;
		this.internalTestHelper = internalTestHelper;

		executorService.submit(this);
	}

	/**
	 * Assures to shut down the Tracker thread
	 */
	public void stopTracking() {
		stop = true;
		executorService.shutdownNow();
	}
	
	@Override
	public void run() {
		StopWatch stopWatch = new StopWatch();
		while(true) {
			if(Thread.currentThread().isInterrupted() || stop) {
				logger.debug("Tracker stopping");
				break;
			}
			
			List<User> userList = internalTestHelper.getAllUsers();
			logger.debug("Begin Tracker. Tracking " + userList.size() + " users.");
			stopWatch.start();
			userList.forEach(visitedLocationService::getUserLastVisitedLocation);
			stopWatch.stop();
			logger.debug("Tracker Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
			stopWatch.reset();
			try {
				logger.debug("Tracker sleeping");
				TimeUnit.SECONDS.sleep(trackingPollingInterval);
			} catch (InterruptedException error) {
				break;
			}
		}
	}
}
