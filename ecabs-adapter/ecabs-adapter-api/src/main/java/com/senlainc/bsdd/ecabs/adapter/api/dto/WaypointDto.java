package com.senlainc.bsdd.ecabs.adapter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WaypointDto extends AEntityDto<String>{

    private String locality;
    private String latitude;
    private String longitude;
}
