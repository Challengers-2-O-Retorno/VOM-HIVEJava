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
@Table(name = "TB_SUBSCRIPTION")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SUBSCRIPTION")
    @SequenceGenerator(name = "SQ_SUBSCRIPTION", sequenceName = "SQ_SUBSCRIPTION", allocationSize = 1)
    @Column(name = "ID_SUBSCRIPTION")
    private Long id_subscription;

    @Column(name = "VALUE_SUBSCRIPTION")
    private double value;

    @Column(name = "STATUS_SUBSCRIPTION")
    private String status;

    @Column(name = "DT_START_SUBSCRIPTION")
    private LocalDate dt_start;

    @Column(name = "DT_END_SUBSCRIPTION")
    private LocalDate dt_end;

    //Relacionamento ManyToMany
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_SUBSCRIPTION_PAY_HIST",
            joinColumns = {
                    @JoinColumn(
                            name = "SUBSCRIPTION",
                            referencedColumnName = "ID_SUBSCRIPTION",
                            foreignKey = @ForeignKey(
                                    name = "FK_SUBSCRIPTION_PAY_HIST"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "PAY_HIST",
                            referencedColumnName = "ID_PAY_HIST",
                            foreignKey = @ForeignKey(
                                    name = "FK_PAY_HIST_SUBSCRIPTION"
                            )
                    )
            }
    )
    private Set<Pay> pagamentos = new LinkedHashSet<>();

    //Relacionamento ManyToOne
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "COMPANY",
            referencedColumnName = "ID_COMPANY",
            foreignKey = @ForeignKey(
                    name = "FK_SUBSCRIPTION_COMPANY"
            )
    )
    private Company company;

}
