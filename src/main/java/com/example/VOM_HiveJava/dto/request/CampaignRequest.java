package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

public record CampaignRequest(

        @NotNull(message = "O nome da campanha é obrigatório")
        String nm_campaign,

        @NotNull(message = "O alvo da campanha é obrigatório")
        String target,

        @NotNull(message = "Os detalhes da campanha são obrigatórios")
        byte[] details,

        String status,

        @PastOrPresent(message = "A data de registro não pode ser no futuro")
        @NotNull(message = "A data de registro é obrigatória")
        LocalDate dt_register

) {
}
