package com.example.AddressJoin.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "district_master")
public class DistrictMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private StateMaster stateMaster;

    @OneToMany(mappedBy = "districtMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubDistrictMaster> subDistricts;  // Change from 'parts' to 'subDistricts'
}
