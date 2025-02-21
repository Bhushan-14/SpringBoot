package com.example.AddressJoin.mapper;

import com.example.AddressJoin.dao.entities.CountryMaster;
import com.example.AddressJoin.dto.CountryMasterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface CountryMasterMapper {
    CountryMasterMapper INSTANCE = Mappers.getMapper(CountryMasterMapper.class);

    CountryMasterDTO toDTO(CountryMaster countryMaster);
    List<CountryMasterDTO> toDTOList(List<CountryMaster> countryMasters);
}
