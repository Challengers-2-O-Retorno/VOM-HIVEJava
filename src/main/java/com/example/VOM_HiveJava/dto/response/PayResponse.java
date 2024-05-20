package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PayResponse(

        Long idHistory,
        double value,
        byte[] nef,
        String method,
        LocalDate dtPayment,
        LocalDate dtDue
) {
}
