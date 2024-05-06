package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

public record CampaignRequest(

        @NotNull(message = "O nome da campanha é obrigatório")
        String nm_campaign,

        String target,

        byte[] details,

        String status,

        LocalDate dt_register

) {
}
