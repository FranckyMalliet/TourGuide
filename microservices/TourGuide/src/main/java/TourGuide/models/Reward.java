package tourGuide.models;


import tourGuide.beans.AttractionBean;
import tourGuide.beans.VisitedLocationBean;

public class Reward {

	private VisitedLocationBean visitedLocation;
	private AttractionBean attraction;
	private int rewardPoints;

	public Reward(VisitedLocationBean visitedLocation, AttractionBean attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}

	public Reward(VisitedLocationBean visitedLocation, AttractionBean attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	public VisitedLocationBean getVisitedLocation() {
		return visitedLocation;
	}

	public AttractionBean getAttraction() {
		return attraction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
