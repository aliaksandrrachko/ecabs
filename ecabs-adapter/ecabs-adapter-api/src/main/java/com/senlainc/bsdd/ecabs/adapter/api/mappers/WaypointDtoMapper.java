package com.senlainc.bsdd.ecabs.adapter.api.mappers;

import com.senlainc.bsdd.ecabs.adapter.api.dto.WaypointDto;
import com.senlainc.bsdd.ecabs.adapter.entity.entities.Waypoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = NonBuilderMapperConfig.class)
public interface WaypointDtoMapper {

    WaypointDto toDto(Waypoint entity);
    Waypoint toEntity(WaypointDto dto);
}
