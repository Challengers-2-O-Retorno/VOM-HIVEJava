package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Builder
public record CompanyResponse(

        Long idCompany,
        String nmCompany,
        String cnpj,
        String email,
        LocalDate dtRegister,
        Collection<ProfileResponse> profiles
) {
}
