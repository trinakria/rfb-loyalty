package org.trinakria.rfbloyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trinakria.rfbloyalty.domain.RfbUser;


/**
 * Spring Data JPA repository for the RfbUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbUserRepository extends JpaRepository<RfbUser, Long> {

}
