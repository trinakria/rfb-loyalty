package org.trinakria.rfbloyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trinakria.rfbloyalty.domain.RfbEvent;
import org.trinakria.rfbloyalty.domain.RfbLocation;

import java.time.LocalDate;


/**
 * Spring Data JPA repository for the RfbEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbEventRepository extends JpaRepository<RfbEvent, Long> {

    RfbEvent findByRfbLocationAndEventDate(RfbLocation location, LocalDate date);
}
