package com.example.AddressJoin.services;

import java.util.List;

import com.example.AddressJoin.dto.CountryMasterDTO;
import com.example.AddressJoin.dto.DistrictMasterDTO;
import com.example.AddressJoin.dto.StateMasterDTO;
import com.example.AddressJoin.dto.SubDistrictMasterDTO;
import com.example.AddressJoin.dto.response.AddressApiResponse;

public interface AddressService {
    // Get all data
    AddressApiResponse<CountryMasterDTO> getCountryWithStatesDistrictsSubDistricts(Long countryId);
    AddressApiResponse<StateMasterDTO> getStatesByCountry(Long countryId);
    AddressApiResponse<DistrictMasterDTO> getDistrictsByState(Long stateId);
    AddressApiResponse<SubDistrictMasterDTO> getSubDistrictsByDistrict(Long districtId);

    // Create operations
    AddressApiResponse<CountryMasterDTO> addCountry(CountryMasterDTO countryDTO);
    AddressApiResponse<StateMasterDTO> addState(StateMasterDTO stateDTO);
    AddressApiResponse<DistrictMasterDTO> addDistrict(DistrictMasterDTO districtDTO);
    AddressApiResponse<SubDistrictMasterDTO> addSubDistrict(SubDistrictMasterDTO subDistrictDTO);

    // Update operations
    AddressApiResponse<CountryMasterDTO> updateCountry(Long countryId, CountryMasterDTO countryDTO);
    AddressApiResponse<StateMasterDTO> updateState(Long stateId, StateMasterDTO stateDTO);
    AddressApiResponse<DistrictMasterDTO> updateDistrict(Long districtId, DistrictMasterDTO districtDTO);
    AddressApiResponse<SubDistrictMasterDTO> updateSubDistrict(Long subDistrictId, SubDistrictMasterDTO subDistrictDTO);

    // Delete operations
    AddressApiResponse<Void> deleteCountry(Long countryId);
    AddressApiResponse<Void> deleteState(Long stateId);
    AddressApiResponse<Void> deleteDistrict(Long districtId);
    AddressApiResponse<Void> deleteSubDistrict(Long subDistrictId);
    
    AddressApiResponse<List<CountryMasterDTO>> getFilteredAddresses(
    	    List<Long> countryIds, List<Long> stateIds, List<Long> districtIds, List<Long> subDistrictIds);

}
