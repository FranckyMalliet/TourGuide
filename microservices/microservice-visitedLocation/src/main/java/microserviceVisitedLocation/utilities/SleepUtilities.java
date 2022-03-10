package microserviceVisitedLocation.utilities;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SleepUtilities {

    public void sleep() {
        int random = ThreadLocalRandom.current().nextInt(2, 5);

        try {
            TimeUnit.MILLISECONDS.sleep(random);
        } catch (InterruptedException var3) {
        }
    }
}
