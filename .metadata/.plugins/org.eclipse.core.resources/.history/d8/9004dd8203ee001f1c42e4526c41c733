package com.example.AddressJoin.controllers;

import com.example.AddressJoin.dto.CountryMasterDTO;
import com.example.AddressJoin.dto.DistrictMasterDTO;
import com.example.AddressJoin.dto.StateMasterDTO;
import com.example.AddressJoin.dto.SubDistrictMasterDTO;
import com.example.AddressJoin.dto.response.AddressApiResponse;

public interface AddressMasterController {

    AddressApiResponse<CountryMasterDTO> getCountryWithStatesDistrictsSubDistricts(Long countryId);

    AddressApiResponse<StateMasterDTO> getStatesByCountry(Long countryId);

    AddressApiResponse<DistrictMasterDTO> getDistrictsByState(Long stateId);

    AddressApiResponse<SubDistrictMasterDTO> getSubDistrictsByDistrict(Long districtId);
}
