package com.example.VOM_HiveJava.dto.response;

import java.time.LocalDate;

public record SubscriptionResponse(

        int id_subscription,
        double value,
        String status,
        LocalDate dt_start,
        LocalDate dt_end,
        PayResponse pagamentos,
        CompanyResponse company
) {
}
