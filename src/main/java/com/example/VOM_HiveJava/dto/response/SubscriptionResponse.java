package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Collection;

@Builder
public record SubscriptionResponse(

        Long idSubscription,
        double value,
        String status,
        LocalDate dtStart,
        LocalDate dtEnd,
        Collection<PayResponse> pagamentos,
        CompanyResponse company
) {
}
