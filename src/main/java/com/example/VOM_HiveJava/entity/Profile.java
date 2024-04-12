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
@Table(name = "TB_PROFILE")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROFILE")
    @SequenceGenerator(name = "SQ_PROFILE", sequenceName = "SQ_PROFILE", allocationSize = 1)
    @Column(name = "ID_USER_PROFILE")
    private int id_user;

    @Column(name = "NM_USER_PROFILE")
    private String nm_user;

    @Column(name = "PASS_USER_PROFILE")
    private String pass_user;

    @Column(name = "PERMISSION_PROFILE")
    private String permission;

    @Column(name = "STATUS_PROFILE")
    private String status;

    @Column(name = "DT_REGISTER_PROFILE")
    private LocalDate dt_register;

}
