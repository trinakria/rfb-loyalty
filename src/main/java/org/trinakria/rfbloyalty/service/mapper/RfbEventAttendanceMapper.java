package org.trinakria.rfbloyalty.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.trinakria.rfbloyalty.domain.RfbEventAttendance;
import org.trinakria.rfbloyalty.service.dto.RfbEventAttendanceDTO;

/**
 * Mapper for the entity RfbEventAttendance and its DTO RfbEventAttendanceDTO.
 */
@Mapper(componentModel = "spring", uses = {RfbEventMapper.class, RfbUserMapper.class})
public interface RfbEventAttendanceMapper extends EntityMapper<RfbEventAttendanceDTO, RfbEventAttendance> {

    @Mapping(source = "rfbEvent.id", target = "rfbEventId")
    @Mapping(source = "rfbUser.id", target = "rfbUserId")
    RfbEventAttendanceDTO toDto(RfbEventAttendance rfbEventAttendance);

    @Mapping(source = "rfbEventId", target = "rfbEvent")
    @Mapping(source = "rfbUserId", target = "rfbUser")
    RfbEventAttendance toEntity(RfbEventAttendanceDTO rfbEventAttendanceDTO);

    default RfbEventAttendance fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbEventAttendance rfbEventAttendance = new RfbEventAttendance();
        rfbEventAttendance.setId(id);
        return rfbEventAttendance;
    }
}
