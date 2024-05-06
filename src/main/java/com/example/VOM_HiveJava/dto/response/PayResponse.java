package com.example.VOM_HiveJava.dto.response;

import java.time.LocalDate;

public record PayResponse(

        int id_history,
        double value,
        byte[] nef,
        String method,
        LocalDate dt_payment,
        LocalDate dt_due
) {
}
