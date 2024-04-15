package com.example.VOM_HiveJava.dto.response;

import java.time.LocalDate;

public record CompanyResponse(

        int id_company,
        String nm_company,
        String cnpj,
        String email,
        LocalDate dt_register,
        ProfileResponse profile
) {
}
