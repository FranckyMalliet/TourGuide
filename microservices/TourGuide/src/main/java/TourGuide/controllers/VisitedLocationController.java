package tourGuide.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.beans.VisitedLocationBean;
import tourGuide.helper.InternalTestHelper;
import tourGuide.services.UserService;
import tourGuide.services.VisitedLocationService;

import java.util.List;
import java.util.Map;

@RestController
public class VisitedLocationController {

    private final static Logger logger = LoggerFactory.getLogger(VisitedLocationController.class);
    private VisitedLocationService visitedLocationService;
    private UserService userService;
    private final InternalTestHelper internalTestHelper;
    private final ObjectMapper mapper = new ObjectMapper();

    public VisitedLocationController(VisitedLocationService visitedLocationService, UserService userService, InternalTestHelper internalTestHelper){
        this.visitedLocationService = visitedLocationService;
        this.userService = userService;
        this.internalTestHelper = internalTestHelper;
    }

    @RequestMapping("/getLastVisitedLocation")
    public String getLastVisitedLocation(@RequestParam String userName) throws JsonProcessingException {
        VisitedLocationBean visitedLocation = visitedLocationService.getUserLastVisitedLocation(userService.findUserByUserName(internalTestHelper.getAllUsers(), userName));
        logger.info("Retrieving location of a user named : " + userName);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(visitedLocation);
    }

    @RequestMapping("/getAllUsersCurrentVisitedLocations")
    public String getAllUsersCurrentVisitedLocations() throws JsonProcessingException {
        Map<String, List<Double>> usersMostRecentLocations = visitedLocationService.findAllMostRecentUsersVisitedLocations(internalTestHelper.getAllUsers());

        logger.info("Retrieving all users most recent location");
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersMostRecentLocations);
    }
}
