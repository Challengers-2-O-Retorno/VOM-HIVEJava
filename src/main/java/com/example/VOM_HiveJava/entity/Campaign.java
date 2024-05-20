package com.example.VOM_HiveJava.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_CAMPAIGN",
                uniqueConstraints = {
        @UniqueConstraint(name = "UK_CAMPAIGN_NOME", columnNames = "NM_CAMPAIGN")
                })
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAMPAIGN")
    @SequenceGenerator(name = "SQ_CAMPAIGN", sequenceName = "SQ_CAMPAIGN", allocationSize = 1)
    @Column(name = "ID_CAMPAIGN")
    private Long idCampaign;

    @Column(name = "NM_CAMPAIGN")
    private String nmCampaign;

    @Column(name = "TARGET_CAMPAIGN")
    private String target;

    @Column(name = "DETAILS_CAMPAIGN")
    private byte[] details;

    @Column(name = "STATUS_CAMPAIGN")
    private String status;

    @Column(name = "DT_REGISTER_CAMPAIGN")
    private LocalDate dtRegister;

    //relacionamento ManyToOne
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "COMPANY",
            referencedColumnName = "ID_COMPANY",
            foreignKey = @ForeignKey(
                    name = "FK_CAMPAIGN_COMPANY"
            )
    )
    private Company company;
    //relacionamento ManyToOne

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PRODUCT",
            referencedColumnName = "ID_PRODUCT",
            foreignKey = @ForeignKey(
                    name = "FK_CAMPAIGN_PRODUCT"
            )
    )
    private Product product;
}
