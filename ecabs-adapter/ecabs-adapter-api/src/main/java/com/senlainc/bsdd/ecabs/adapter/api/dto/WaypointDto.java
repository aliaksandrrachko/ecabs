package com.senlainc.bsdd.ecabs.adapter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WaypointDto extends AEntityDto<String>{

    @NotBlank
    private String locality;
    @NotBlank
    private String latitude;
    @NotBlank
    private String longitude;
}
