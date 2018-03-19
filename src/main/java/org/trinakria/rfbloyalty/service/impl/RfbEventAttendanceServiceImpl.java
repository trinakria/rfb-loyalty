package org.trinakria.rfbloyalty.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trinakria.rfbloyalty.domain.RfbEventAttendance;
import org.trinakria.rfbloyalty.repository.RfbEventAttendanceRepository;
import org.trinakria.rfbloyalty.service.RfbEventAttendanceService;
import org.trinakria.rfbloyalty.service.dto.RfbEventAttendanceDTO;
import org.trinakria.rfbloyalty.service.mapper.RfbEventAttendanceMapper;


/**
 * Service Implementation for managing RfbEventAttendance.
 */
@Service
@Transactional
public class RfbEventAttendanceServiceImpl implements RfbEventAttendanceService{

    private final Logger log = LoggerFactory.getLogger(RfbEventAttendanceServiceImpl.class);

    private final RfbEventAttendanceRepository rfbEventAttendanceRepository;

    private final RfbEventAttendanceMapper rfbEventAttendanceMapper;

    public RfbEventAttendanceServiceImpl(RfbEventAttendanceRepository rfbEventAttendanceRepository, RfbEventAttendanceMapper rfbEventAttendanceMapper) {
        this.rfbEventAttendanceRepository = rfbEventAttendanceRepository;
        this.rfbEventAttendanceMapper = rfbEventAttendanceMapper;
    }

    /**
     * Save a rfbEventAttendance.
     *
     * @param rfbEventAttendanceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RfbEventAttendanceDTO save(RfbEventAttendanceDTO rfbEventAttendanceDTO) {
        log.debug("Request to save RfbEventAttendance : {}", rfbEventAttendanceDTO);
        RfbEventAttendance rfbEventAttendance = rfbEventAttendanceMapper.toEntity(rfbEventAttendanceDTO);
        rfbEventAttendance = rfbEventAttendanceRepository.save(rfbEventAttendance);
        return rfbEventAttendanceMapper.toDto(rfbEventAttendance);
    }

    /**
     * Get all the rfbEventAttendances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RfbEventAttendanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RfbEventAttendances");
        return rfbEventAttendanceRepository.findAll(pageable)
            .map(rfbEventAttendanceMapper::toDto);
    }

    /**
     * Get one rfbEventAttendance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RfbEventAttendanceDTO findOne(Long id) {
        log.debug("Request to get RfbEventAttendance : {}", id);
        RfbEventAttendance rfbEventAttendance = rfbEventAttendanceRepository.findOne(id);
        return rfbEventAttendanceMapper.toDto(rfbEventAttendance);
    }

    /**
     * Delete the rfbEventAttendance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RfbEventAttendance : {}", id);
        rfbEventAttendanceRepository.delete(id);
    }
}
