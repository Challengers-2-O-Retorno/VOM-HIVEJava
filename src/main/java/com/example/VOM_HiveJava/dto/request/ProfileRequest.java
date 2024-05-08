package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ProfileRequest(

        @NotNull(message = "Nome do usuário é obrigatório")
        String nm_user,

        @Pattern(
                message = "A senha não atende aos requisitos",
                regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,16}$/"
        )
        @NotNull(message = "Senha do usuário obrigatório")
        String pass_user,

        String permission,

        String status,

        LocalDate dt_register
) {
}
