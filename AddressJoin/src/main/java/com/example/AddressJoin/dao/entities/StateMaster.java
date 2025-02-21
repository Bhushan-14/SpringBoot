package com.example.AddressJoin.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "state_master")
public class StateMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryMaster countryMaster;

    @OneToMany(mappedBy = "stateMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DistrictMaster> districts;
}
