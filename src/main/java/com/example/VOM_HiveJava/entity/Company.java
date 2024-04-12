package com.example.VOM_HiveJava.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COMPANY")
    @SequenceGenerator(name = "SQ_COMPANY", sequenceName = "SQ_COMPANY", allocationSize = 1)
    @Column(name = "ID_COMPANY")
    private int id_company;

    @Column(name = "NM_COMPANY")
    private String nm_company;

    @Column(name = "CNPJ_COMPANY")
    private String cnpj;

    @Column(name = "EMAIL_COMPANY")
    private String email;

    @Column(name = "DT_REGISTER_COMPANY")
    private LocalDate dt_register;

    //relacionamento ManyToMany
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_COMPANY_PROFILE",
            joinColumns = {
                    @JoinColumn(
                            name = "COMPANY",
                            referencedColumnName = "ID_COMPANY",
                            foreignKey = @ForeignKey(
                                    name = "FK_COMPANY_PROFILE"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "PROFILE",
                            referencedColumnName = "ID_USER_PROFILE",
                            foreignKey = @ForeignKey(
                                    name = "FK_PROFILE_COMPANY"
                            )
                    )
            }
    )
    private Set<Profile> profiles = new LinkedHashSet<>();
}
