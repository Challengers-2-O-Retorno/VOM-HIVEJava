package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PayRequest(

        double valor,
        byte[] nef,
        @NotNull(message = "Campo obrigatório")
        String method,

        @NotNull(message = "Campo obrigatório")
        LocalDate dtPayment,

        @NotNull(message = "Campo obrigatório")
        LocalDate dtDue

) {
}
