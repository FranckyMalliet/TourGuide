package tourGuide.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tourGuide.models.User;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * This method look for a specific user into a list using his userName.
     * @param userList
     * @param userName
     * @return a user object.
     */

    public User findUserByUserName(List<User> userList, String userName){
        User userWeAreLookingFor = new User(UUID.randomUUID(), "null", "null", "null");

        try{
            for(User user : userList){
                if(user.getUserName().equals(userName)){
                    userWeAreLookingFor.setUserId(user.getUserId());
                    userWeAreLookingFor.setUserName(user.getUserName());
                    userWeAreLookingFor.setEmailAddress(user.getEmailAddress());
                    userWeAreLookingFor.setPhoneNumber(user.getPhoneNumber());
                    userWeAreLookingFor.setLatestLocationTimestamp(user.getLatestLocationTimestamp());
                    userWeAreLookingFor.setUserPreferences(user.getUserPreferences());
                }
            }
        } catch(NullPointerException nullPointerException){
            logger.error("No user has been found");
        }

        return userWeAreLookingFor;
    }
}
