package org.trinakria.rfbloyalty.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trinakria.rfbloyalty.domain.RfbEvent;
import org.trinakria.rfbloyalty.domain.RfbLocation;
import org.trinakria.rfbloyalty.repository.RfbEventRepository;
import org.trinakria.rfbloyalty.repository.RfbLocationRepository;
import org.trinakria.rfbloyalty.service.RfbEventService;
import org.trinakria.rfbloyalty.service.dto.RfbEventDTO;
import org.trinakria.rfbloyalty.service.mapper.RfbEventMapper;

import java.time.LocalDate;


/**
 * Service Implementation for managing RfbEvent.
 */
@Service
@Transactional
public class RfbEventServiceImpl implements RfbEventService{

    private final Logger log = LoggerFactory.getLogger(RfbEventServiceImpl.class);

    private final RfbEventRepository rfbEventRepository;

    private final RfbEventMapper rfbEventMapper;

    private final RfbLocationRepository locationRepository;

    public RfbEventServiceImpl(RfbEventRepository rfbEventRepository, RfbEventMapper rfbEventMapper,
                               RfbLocationRepository locationRepository) {
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEventMapper = rfbEventMapper;
        this.locationRepository = locationRepository;
    }

    /**
     * Save a rfbEvent.
     *
     * @param rfbEventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RfbEventDTO save(RfbEventDTO rfbEventDTO) {
        log.debug("Request to save RfbEvent : {}", rfbEventDTO);
        RfbEvent rfbEvent = rfbEventMapper.toEntity(rfbEventDTO);
        rfbEvent = rfbEventRepository.save(rfbEvent);
        return rfbEventMapper.toDto(rfbEvent);
    }

    /**
     * Get all the rfbEvents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RfbEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RfbEvents");
        return rfbEventRepository.findAll(pageable)
            .map(rfbEventMapper::toDto);
    }

    /**
     * Get one rfbEvent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RfbEventDTO findOne(Long id) {
        log.debug("Request to get RfbEvent : {}", id);
        RfbEvent rfbEvent = rfbEventRepository.findOne(id);
        return rfbEventMapper.toDto(rfbEvent);
    }

    /**
     * Delete the rfbEvent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RfbEvent : {}", id);
        rfbEventRepository.delete(id);
    }

    @Override
    public RfbEventDTO findByTodayAndLocation(Long locationID) {
        RfbLocation location = locationRepository.findOne(locationID);
        RfbEvent rfbEvent = rfbEventRepository.findByEventDateEqualsAndRfbLocationEquals(LocalDate.now(),location);
        return rfbEventMapper.toDto(rfbEvent);
    }
}
