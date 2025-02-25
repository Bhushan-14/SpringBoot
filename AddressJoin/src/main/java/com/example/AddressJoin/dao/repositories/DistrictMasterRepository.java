package com.example.AddressJoin.dao.repositories;

import com.example.AddressJoin.dao.entities.DistrictMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface DistrictMasterRepository extends JpaRepository<DistrictMaster, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT d FROM DistrictMaster d " +
           "LEFT JOIN FETCH d.subDistricts sd " +
           "WHERE d.stateMaster.id = :stateId")
    List<DistrictMaster> findDistrictsByStateId(Long stateId);

    List<DistrictMaster> findAll();

    @Query("SELECT d FROM DistrictMaster d WHERE d.name = :name")
    DistrictMaster findByName(String name);
}
