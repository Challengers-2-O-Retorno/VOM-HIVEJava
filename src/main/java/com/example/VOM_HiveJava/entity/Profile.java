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
@Table(name = "TB_PROFILE",
            uniqueConstraints = {
        @UniqueConstraint(name = "UK_PROFILE_ID_NOME", columnNames = {"ID_USER_PROFILE", "NM_USER_PROFILE"})
            })
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROFILE")
    @SequenceGenerator(name = "SQ_PROFILE", sequenceName = "SQ_PROFILE", allocationSize = 1)
    @Column(name = "ID_USER_PROFILE")
    private Long idUser;

    //Nome
    @Column(name = "NM_USER_PROFILE")
    private String nmUser;

    //Password
    @Column(name = "PASS_USER_PROFILE")
    private String passUser;

    //Permissão
    @Column(name = "PERMISSION_PROFILE")
    private String permission;

    //Status
    @Column(name = "STATUS_PROFILE")
    private String status;

    //Data de registro
    @Column(name = "DT_REGISTER_PROFILE")
    private LocalDate dtRegister;

}
