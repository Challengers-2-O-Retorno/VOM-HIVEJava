package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CompanyRequest(

        @NotNull(message = "O nome da companhia é obrigatório")
        String nm_company,

        @Max(value = 14, message = "CNPJ deve ter 14 digitos(desconsidere pontos))")
        @NotNull(message = "O CNPJ da companhia é obrigatório")
        String cnpj,

        @Email(message = "Email inserido inválido")
        @NotNull(message = "O email da companhia é obrigatório")
        String email,

        @PastOrPresent(message = "A data de registro não pode ser no futuro")
        LocalDate dt_register
) {
}
