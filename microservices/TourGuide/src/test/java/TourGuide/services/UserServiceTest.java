package tourGuide.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuide.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByUserNameTest(){
        //GIVEN
        List<User> userList = new ArrayList<>();
        userList.add(new User(UUID.randomUUID(), "Sauron", "0645656565", "Mordor@middleEarth.com"));
        userList.add(new User(UUID.randomUUID(), "Elrond", "0645656565", "Lothlorien@middleEarth.com"));
        userList.add(new User(UUID.randomUUID(), "Gandalf", "0645656565", "Valar@middleEarth.com"));

        String userName = "Sauron";

        //WHEN
        User user = userService.findUserByUserName(userList, userName);

        //THEN
        Assertions.assertEquals(user.getUserName(), userName);
    }
}
