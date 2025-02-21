package com.example.AddressJoin.dao.repositories;

import com.example.AddressJoin.dao.entities.SubDistrictMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SubDistrictMasterRepository extends JpaRepository<SubDistrictMaster, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT sd FROM SubDistrictMaster sd WHERE sd.districtMaster.id = :districtId")
    List<SubDistrictMaster> findSubDistrictsByDistrictId(Long districtId);

    List<SubDistrictMaster> findAll();
    @Query("SELECT sd FROM SubDistrictMaster sd WHERE sd.name = :name")
    SubDistrictMaster findByName(String name);
}
