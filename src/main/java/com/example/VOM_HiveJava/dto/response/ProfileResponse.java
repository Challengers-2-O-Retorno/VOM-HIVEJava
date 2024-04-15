package com.example.VOM_HiveJava.dto.response;

import java.time.LocalDate;

public record ProfileResponse(

        int id_user,
        String nm_user,
        String pass_user,
        String permission,
        String status,
        LocalDate dt_register
) {
}
