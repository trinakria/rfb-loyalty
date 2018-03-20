package org.trinakria.rfbloyalty.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.trinakria.rfbloyalty.domain.RfbEvent;
import org.trinakria.rfbloyalty.domain.RfbLocation;
import org.trinakria.rfbloyalty.repository.RfbEventRepository;
import org.trinakria.rfbloyalty.repository.RfbLocationRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Service to run daily and generate random event codes for RFB runs
 */
@Service
public class RfbEventCodeService {

    private final Logger log = LoggerFactory.getLogger(RfbEventCodeService.class);

    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;

    public RfbEventCodeService(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
    }

    @Scheduled(cron = "0 0 * * * ?") //run once per hour, at top of hour
    //@Scheduled(cron = "0 * * * * ?") //run once per min
    //@Scheduled(cron = "* * * * * ?") //run once per sec
    public void generateRunEventCodes()
    {
        log.debug("Generating Events");

        List<RfbLocation> rfbLocations = rfbLocationRepository.findAllByRunDayOfWeek(LocalDate.now().getDayOfWeek().getValue());

        log.debug("Locations found for events {}", rfbLocations.size());

        rfbLocations.forEach(location -> {
            log.debug("Checking events for location {}", location.getId());
            RfbEvent existingEvent = rfbEventRepository.findByRfbLocationAndEventDate(location, LocalDate.now());

            if (existingEvent == null) {
                log.debug("Event not found. Creating event");
                RfbEvent newEvent = new RfbEvent();
                newEvent.setRfbLocation(location);
                newEvent.setEventDate(LocalDate.now());
                newEvent.setEventCode(RandomStringUtils.randomAlphanumeric(6).toUpperCase());

                rfbEventRepository.save(newEvent);

                log.debug("Created event {}", newEvent);
            } else {
                log.debug("Event exist for the day");
            }
        });
    }
}
