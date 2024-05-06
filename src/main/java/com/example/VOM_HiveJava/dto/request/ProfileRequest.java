package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ProfileRequest(

        @NotNull(message = "Nome do usuário é obrigatório")
        String nm_user,

        @NotNull(message = "Senha do usuário obrigatório")
        String pass_user,

        String permission,

        String status,

        LocalDate dt_register
) {
}
