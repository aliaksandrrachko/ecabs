package com.senlainc.bsdd.ecabs.adapter.api.mappers;

import com.senlainc.bsdd.ecabs.adapter.api.dto.BookingDto;
import com.senlainc.bsdd.ecabs.adapter.entity.entities.Booking;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = NonBuilderMapperConfig.class,
        uses = {WaypointDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookingDtoMapper {

    BookingDto toDto(Booking entity);
    Booking toEntity(BookingDto dto);
}
