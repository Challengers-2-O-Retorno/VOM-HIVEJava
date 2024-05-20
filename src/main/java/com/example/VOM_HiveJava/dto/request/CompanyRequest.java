package com.example.VOM_HiveJava.dto.request;

import com.example.VOM_HiveJava.dto.response.ProfileResponse;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

public record CompanyRequest(

        @Size(min = 10, max = 255)
        @NotNull(message = "O nome da companhia é obrigatório")
        String nmCompany,

        @CNPJ
        @NotNull(message = "O CNPJ da companhia é obrigatório")
        String cnpj,

        @Email(message = "Email inserido inválido")
        @NotNull(message = "O email da companhia é obrigatório")
        String email,

        LocalDate dtRegister,

        ProfileResponse profiles


) {
}
