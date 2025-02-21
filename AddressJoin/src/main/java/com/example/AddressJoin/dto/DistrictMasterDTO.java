package com.example.AddressJoin.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class DistrictMasterDTO {
    private Long id;
    private String name;
    private Long stateId;  
    private List<SubDistrictMasterDTO> subDistricts;
}
