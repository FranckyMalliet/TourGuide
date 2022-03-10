package tourGuide.controllers;

import com.jsoniter.output.JsonStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.helper.InternalTestHelper;
import tourGuide.services.RewardService;
import tourGuide.services.UserService;

@RestController
public class RewardController {

    private final static Logger logger = LoggerFactory.getLogger(RewardController.class);
    private RewardService rewardService;
    private UserService userService;
    private final InternalTestHelper internalTestHelper;

    public RewardController(RewardService rewardService, UserService userService, InternalTestHelper internalTestHelper){
        this.rewardService = rewardService;
        this.userService = userService;
        this.internalTestHelper = internalTestHelper;
    }

    @RequestMapping("/getRewards")
    public String getRewards(@RequestParam String userName) {
        //logger.info("Retrieving rewards of a user named : " + userName);
        return JsonStream.serialize(rewardService.getUserRewards(userService.findUserByUserName(internalTestHelper.getAllUsers(), userName)));
    }
}
