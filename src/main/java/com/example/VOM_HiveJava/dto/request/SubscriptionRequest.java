package com.example.VOM_HiveJava.dto.request;

import com.example.VOM_HiveJava.dto.response.CompanyResponse;
import com.example.VOM_HiveJava.dto.response.PayResponse;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.aspectj.bridge.IMessage;

import java.time.LocalDate;

public record SubscriptionRequest(

        double value,

        String status,

        LocalDate dtStart,


        LocalDate dtEnd,

        PayResponse pagamentos,

        CompanyResponse company
) {
}
