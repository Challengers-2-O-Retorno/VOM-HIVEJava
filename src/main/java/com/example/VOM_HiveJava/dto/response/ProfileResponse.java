package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProfileResponse(

        Long idUser,
        String nmUser,
        String passUser,
        String permission,
        String status,
        LocalDate dtRegister
) {
}
