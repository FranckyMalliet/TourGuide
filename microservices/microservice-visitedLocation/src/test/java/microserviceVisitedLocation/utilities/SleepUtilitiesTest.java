package microserviceVisitedLocation.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SleepUtilitiesTest {

    @Test
    public void sleepTest(){
        SleepUtilities sleepUtilities = mock(SleepUtilities.class);
        doCallRealMethod().when(sleepUtilities).sleepLighter();
        sleepUtilities.sleep();
        verify(sleepUtilities).sleep();
    }

    @Test
    public void sleepLighter(){
        SleepUtilities sleepUtilities = mock(SleepUtilities.class);
        doCallRealMethod().when(sleepUtilities).sleepLighter();
        sleepUtilities.sleepLighter();
        verify(sleepUtilities).sleepLighter();
    }
}
