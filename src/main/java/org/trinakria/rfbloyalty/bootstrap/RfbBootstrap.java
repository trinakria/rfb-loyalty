package org.trinakria.rfbloyalty.bootstrap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trinakria.rfbloyalty.domain.RfbEvent;
import org.trinakria.rfbloyalty.domain.RfbEventAttendance;
import org.trinakria.rfbloyalty.domain.RfbLocation;
import org.trinakria.rfbloyalty.domain.User;
import org.trinakria.rfbloyalty.repository.*;

import java.time.DayOfWeek;
import java.time.LocalDate;


/**
 * Created by jt on 10/14/17.
 */
@Component
public class RfbBootstrap implements CommandLineRunner {

    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;
    private final RfbEventAttendanceRepository rfbEventAttendanceRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public RfbBootstrap(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository,
                        RfbEventAttendanceRepository rfbEventAttendanceRepository, UserRepository userRepository,
                        PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEventAttendanceRepository = rfbEventAttendanceRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    @Override
    public void run(String... strings) throws Exception {

        // init RFB Locations
        if (rfbLocationRepository.count() == 0) {
            //only load data if no data loaded
            initData();
        }

    }

    private void initData() {
        User user = new User();
        user.setFirstName("Johnny");
        user.setLogin("johnny");
        user.setEmail("johnny@runningforbrews.com");
        user.setActivated(true);
        user.setPassword(passwordEncoder.encode("admin"));
        user.addAuthority(authorityRepository.findOne("ROLE_RUNNER"));
        user.addAuthority(authorityRepository.findOne("ROLE_ORGANIZER"));
        userRepository.save(user);

        //load data
        RfbLocation aleAndWitch = getRfbLocation("St Pete - Ale and the Witch", DayOfWeek.MONDAY.getValue());

        user.setHomeLocation(aleAndWitch);
        userRepository.save(user);

        RfbEvent aleEvent = getRfbEvent(aleAndWitch);

        getRfbEventAttendance(user, aleEvent);

        RfbLocation ratc = getRfbLocation("St Pete - Right Around The Corner", DayOfWeek.TUESDAY.getValue());

        RfbEvent ratcEvent = getRfbEvent(ratc);

        getRfbEventAttendance(user, ratcEvent);

        RfbLocation stPeteBrew = getRfbLocation("St Pete - St Pete Brewing", DayOfWeek.WEDNESDAY.getValue());

        RfbEvent stPeteBrewEvent = getRfbEvent(stPeteBrew);

        getRfbEventAttendance(user, stPeteBrewEvent);

        RfbLocation yardOfAle = getRfbLocation("St Pete - Yard of Ale", DayOfWeek.THURSDAY.getValue());

        RfbEvent yardOfAleEvent = getRfbEvent(yardOfAle);

        getRfbEventAttendance(user, yardOfAleEvent);

        RfbLocation pourHouse = getRfbLocation("Tampa - Pour House", DayOfWeek.MONDAY.getValue());
        RfbLocation macDintons = getRfbLocation("Tampa - Mac Dintons", DayOfWeek.TUESDAY.getValue());
        RfbLocation satRun = getRfbLocation("Saturday Run for testing", DayOfWeek.SATURDAY.getValue());

    }


    private void getRfbEventAttendance(User rfbUser, RfbEvent rfbEvent) {
        RfbEventAttendance rfbAttendance = new RfbEventAttendance();
        rfbAttendance.setRfbEvent(rfbEvent);
        rfbAttendance.setUser(rfbUser);
        rfbAttendance.setAttendanceDate(LocalDate.now());

        System.out.println(rfbAttendance.toString());

        rfbEventAttendanceRepository.save(rfbAttendance);
        rfbEventRepository.save(rfbEvent);
    }

    private RfbEvent getRfbEvent(RfbLocation rfbLocation) {
        RfbEvent rfbEvent = new RfbEvent();
        rfbEvent.setEventCode(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        rfbEvent.setEventDate(LocalDate.now()); // will not be on assigned day...
        rfbLocation.addRfbEvent(rfbEvent);
        rfbLocationRepository.save(rfbLocation);
        rfbEventRepository.save(rfbEvent);
        return rfbEvent;
    }

    private RfbLocation getRfbLocation(String locationName, int value) {
        RfbLocation rfbLocation = new RfbLocation();
        rfbLocation.setLocationName(locationName);
        rfbLocation.setRunDayOfWeek(value);
        rfbLocationRepository.save(rfbLocation);
        return rfbLocation;
    }
}
