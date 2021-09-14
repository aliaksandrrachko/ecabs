package com.senlainc.bsdd.ecabs.adapter.entity.entities;

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
public class Waypoint extends AEntity<Long>{

    private String locality;
    private String latitude;
    private String longitude;
}
