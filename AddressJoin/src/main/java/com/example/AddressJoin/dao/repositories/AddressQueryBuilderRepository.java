package com.example.AddressJoin.dao.repositories;

import com.example.AddressJoin.dao.entities.CountryMaster;
import java.util.List;

public interface AddressQueryBuilderRepository {
    List<CountryMaster> findDynamicAddresses(List<Long> countryIds, List<Long> stateIds, List<Long> districtIds, List<Long> subDistrictIds);
}
