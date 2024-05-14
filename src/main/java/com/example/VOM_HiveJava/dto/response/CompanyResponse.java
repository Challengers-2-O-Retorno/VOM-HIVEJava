package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Builder
public record CompanyResponse(

        Long id_company,
        String nm_company,
        String cnpj,
        String email,
        LocalDate dt_register,
        Collection<ProfileResponse> profiles
) {
}
