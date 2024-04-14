package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ProfileRequest(

        @NotNull(message = "Nome do usuário é obrigatório")
        String nm_user,

        @NotNull(message = "Campo obrigatório")
        String pass_user,

        @NotNull(message = "Permissão obrigatória")
        String permission,

        String status,

        @PastOrPresent(message = "A data de registro n pode ser no futuro")
        @NotNull(message = "Data de registro obrigatória")
        LocalDate dt_register
) {
}
