package com.senlainc.bsdd.ecabs.adapter.api.dto;

import com.senlainc.bsdd.ecabs.adapter.entity.entities.Waypoint;
import com.senlainc.bsdd.ecabs.adapter.entity.meta.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingDto extends AEntityDto<String>{

    private String passengerName;
    private PhoneNumber contactNumber;
    private LocalDateTime pickupTime;
    private boolean asap;
    private Duration waitingTime;
    private int numberOfPassengers;
    private BigDecimal price;
    private double rating;
    private LocalDateTime createdOn;
    private LocalDateTime lastModifiedOn;
    private Set<WaypointDto> tripWaypoints;
}
