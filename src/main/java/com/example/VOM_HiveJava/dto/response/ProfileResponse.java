package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProfileResponse(

        Long id_user,
        String nm_user,
        String pass_user,
        String permission,
        String status,
        LocalDate dt_register
) {
}
