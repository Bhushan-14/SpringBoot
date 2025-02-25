package com.example.AddressJoin.mapper;

import com.example.AddressJoin.dao.entities.CountryMaster;
import com.example.AddressJoin.dao.entities.DistrictMaster;
import com.example.AddressJoin.dao.entities.StateMaster;
import com.example.AddressJoin.dao.entities.SubDistrictMaster;
import com.example.AddressJoin.dto.CountryMasterDTO;
import com.example.AddressJoin.dto.DistrictMasterDTO;
import com.example.AddressJoin.dto.StateMasterDTO;
import com.example.AddressJoin.dto.SubDistrictMasterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMasterMapper {

    AddressMasterMapper INSTANCE = Mappers.getMapper(AddressMasterMapper.class);

    // Country Mapping
    CountryMaster toCountryMasterEntity(CountryMasterDTO dto);
    CountryMasterDTO toCountryMasterDTO(CountryMaster entity);

    // State Mapping
    @Mapping(source = "countryId", target = "countryMaster.id")
    StateMaster toStateMasterEntity(StateMasterDTO dto);

    @Mapping(source = "countryMaster.id", target = "countryId")
    StateMasterDTO toStateMasterDTO(StateMaster entity);

    // District Mapping (Fix)
    @Mapping(source = "stateId", target = "stateMaster.id")
    DistrictMaster toDistrictMasterEntity(DistrictMasterDTO dto);

    @Mapping(source = "stateMaster.id", target = "stateId")
    DistrictMasterDTO toDistrictMasterDTO(DistrictMaster entity); // Corrected method

    // Sub-District Mapping (Fix)
    @Mapping(source = "districtId", target = "districtMaster.id")
    SubDistrictMaster toSubDistrictMasterEntity(SubDistrictMasterDTO dto);

    @Mapping(source = "districtMaster.id", target = "districtId")
    SubDistrictMasterDTO toSubDistrictMasterDTO(SubDistrictMaster entity); // Corrected method

    // List Mapping Methods (Ensure correct method signatures)
    List<CountryMasterDTO> toCountryMasterDTOList(List<CountryMaster> entityList);
    List<StateMasterDTO> toStateMasterDTOList(List<StateMaster> entityList);
    List<DistrictMasterDTO> toDistrictMasterDTOList(List<DistrictMaster> entityList); // Fixed
    List<SubDistrictMasterDTO> toSubDistrictMasterDTOList(List<SubDistrictMaster> entityList); // Fixed
}