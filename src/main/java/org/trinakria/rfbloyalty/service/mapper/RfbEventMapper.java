package org.trinakria.rfbloyalty.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.trinakria.rfbloyalty.domain.RfbEvent;
import org.trinakria.rfbloyalty.service.dto.RfbEventDTO;

/**
 * Mapper for the entity RfbEvent and its DTO RfbEventDTO.
 */
@Mapper(componentModel = "spring", uses = {RfbLocationMapper.class, })
public interface RfbEventMapper extends EntityMapper <RfbEventDTO, RfbEvent> {

    @Mapping(source = "rfbLocation", target = "rfbLocationDTO")
    RfbEventDTO toDto(RfbEvent rfbEvent);

    @Mapping(source = "rfbLocationDTO", target = "rfbLocation")
    @Mapping(target = "rfbEventAttendances", ignore = true)
    RfbEvent toEntity(RfbEventDTO rfbEventDTO);
    default RfbEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbEvent rfbEvent = new RfbEvent();
        rfbEvent.setId(id);
        return rfbEvent;
    }
}
