package tourGuide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TourGuideController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }
}
