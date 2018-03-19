package org.trinakria.rfbloyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trinakria.rfbloyalty.domain.RfbLocation;


/**
 * Spring Data JPA repository for the RfbLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbLocationRepository extends JpaRepository<RfbLocation, Long> {

}
