package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Collection;

@Builder
public record SubscriptionResponse(

        Long id_subscription,
        double value,
        String status,
        LocalDate dt_start,
        LocalDate dt_end,
        Collection<PayResponse> pagamentos,
        CompanyResponse company
) {
}
