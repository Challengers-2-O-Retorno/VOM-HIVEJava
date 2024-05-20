package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProfileRequest(

        @Size(min = 15, max = 100)
        @NotNull(message = "Nome do usuário é obrigatório")
        String nmUser,

        @Pattern(
                message = "A senha deve ter no mínimo uma letra minúscula, uma letra maiúscula, " +
                        "ter no minímo 8 digitos e no máximo 16, e por último, " +
                        "deve conter pelo menos um dos seguintes caracteres especiais $, &, @, #",
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$&@#])[0-9a-zA-Z$&@#]{8,16}$"
        )

        @NotNull(message = "Senha do usuário obrigatório")
        String passUser,

        String permission,

        String status,

        LocalDate dtRegister
) {
}
