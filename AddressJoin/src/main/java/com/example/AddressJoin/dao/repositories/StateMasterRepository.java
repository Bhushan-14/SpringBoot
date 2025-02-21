package com.example.AddressJoin.dao.repositories;

import com.example.AddressJoin.dao.entities.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMaster, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT s FROM StateMaster s " +
           "LEFT JOIN FETCH s.districts d " +
           "LEFT JOIN FETCH d.subDistricts sd " +
           "WHERE s.countryMaster.id = :countryId")
    List<StateMaster> findStatesByCountryId(Long countryId);

    List<StateMaster> findAll();

    @Query("SELECT s FROM StateMaster s WHERE s.name = :name")
    StateMaster findByName(String name);
}
