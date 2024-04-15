package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.aspectj.bridge.IMessage;

import java.time.LocalDate;

public record SubscriptionRequest(

        double value,

        String status,

        @PastOrPresent(message = "A data de início não pode ser no futuro")
        LocalDate dt_start,

        @FutureOrPresent
        LocalDate dt_end
) {
}
