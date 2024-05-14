package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PayResponse(

        Long id_history,
        double value,
        byte[] nef,
        String method,
        LocalDate dt_payment,
        LocalDate dt_due
) {
}
