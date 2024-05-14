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
@Table(name = "TB_PAY_HIST")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PAY_HIST")
    @SequenceGenerator(name = "SQ_PAY_HIST", sequenceName = "SQ_PAY_HIST", allocationSize = 1)
    @Column(name = "ID_PAY_HIST")
    private Long id_history;

    @Column(name = "VALUE_HISTORY")
    private double value;

    @Column(name = "NFE_HIST")
    private byte[] nef;

    @Column(name = "METHOD_HIST")
    private String method;

    @Column(name = "DT_PAY_HIST")
    private LocalDate dt_payment;

    @Column(name = "DT_DUE_HIST")
    private LocalDate dt_due;

}
