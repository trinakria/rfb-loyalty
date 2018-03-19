package org.trinakria.rfbloyalty.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.trinakria.rfbloyalty.domain.RfbLocation;
import org.trinakria.rfbloyalty.service.dto.RfbLocationDTO;

/**
 * Mapper for the entity RfbLocation and its DTO RfbLocationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RfbLocationMapper extends EntityMapper<RfbLocationDTO, RfbLocation> {



    @Mapping(target = "rfbEvents", ignore = true)
    RfbLocation toEntity(RfbLocationDTO rfbLocationDTO);

    default RfbLocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbLocation rfbLocation = new RfbLocation();
        rfbLocation.setId(id);
        return rfbLocation;
    }
}
