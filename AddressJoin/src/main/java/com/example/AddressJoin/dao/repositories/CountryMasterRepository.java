package com.example.AddressJoin.dao.repositories;

import com.example.AddressJoin.dao.entities.CountryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface CountryMasterRepository extends JpaRepository<CountryMaster, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT c FROM CountryMaster c " +
           "LEFT JOIN FETCH c.states s " +
           "LEFT JOIN FETCH s.districts d " +
           "LEFT JOIN FETCH d.subDistricts sd " +
           "WHERE c.id = :countryId")
    CountryMaster findCountryWithStatesDistrictsSubDistricts(Long countryId);


    List<CountryMaster> findAll();

    @Query("SELECT c FROM CountryMaster c WHERE c.name = :name")
    CountryMaster findByName(String name);
}
