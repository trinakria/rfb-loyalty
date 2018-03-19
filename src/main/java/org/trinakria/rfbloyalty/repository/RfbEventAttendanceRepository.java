package org.trinakria.rfbloyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trinakria.rfbloyalty.domain.RfbEventAttendance;


/**
 * Spring Data JPA repository for the RfbEventAttendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbEventAttendanceRepository extends JpaRepository<RfbEventAttendance, Long> {

}
