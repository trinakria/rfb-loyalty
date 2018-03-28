package org.trinakria.rfbloyalty.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.trinakria.rfbloyalty.RfbloyaltyApp;
import org.trinakria.rfbloyalty.bootstrap.RfbBootstrap;
import org.trinakria.rfbloyalty.domain.RfbLocation;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RfbloyaltyApp.class})
public class RfbLocationRepositoryTest extends AbstractRepositoryTest {

    @Before
    public void setUp() {
        RfbBootstrap rfbBootstrap = new RfbBootstrap(rfbLocationRepository, rfbEventRepository,
            rfbEventAttendanceRepository, userRepository, passwordEncoder, authorityRepository);
    }

    @Test
    public void findAllByRunDayOfWeek() {
        List<RfbLocation> mondayLocations = rfbLocationRepository.findAllByRunDayOfWeek(DayOfWeek.MONDAY.getValue());
        List<RfbLocation> tuesdayLocations = rfbLocationRepository.findAllByRunDayOfWeek(DayOfWeek.TUESDAY.getValue());
        List<RfbLocation> wednesdayLocations = rfbLocationRepository.findAllByRunDayOfWeek(DayOfWeek.WEDNESDAY.getValue());

        assertEquals(2, mondayLocations.size());
        assertEquals(2, tuesdayLocations.size());
        assertEquals(1, wednesdayLocations.size());
    }
}
