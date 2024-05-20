package com.example.VOM_HiveJava.dto.request;

import com.example.VOM_HiveJava.dto.response.CompanyResponse;
import com.example.VOM_HiveJava.dto.response.ProductResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import jdk.jshell.Snippet;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

public record CampaignRequest(

        @Size(min = 10, max = 255)
        @NotNull(message = "O nome da campanha é obrigatório")
        String nmCampaign,

        @Size(min = 10, max = 255)
        String target,

        byte[] details,

        String status,

        LocalDate dtRegister,

        CompanyResponse company,

        ProductResponse product



) {

}
