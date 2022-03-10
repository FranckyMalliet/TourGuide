package microserviceTrip.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SleepUtilitiesTest {

    @Test
    public void sleepTest(){
        SleepUtilities sleepUtilities = mock(SleepUtilities.class);
        doCallRealMethod().when(sleepUtilities).sleep();
        sleepUtilities.sleep();
        verify(sleepUtilities).sleep();
    }
}
