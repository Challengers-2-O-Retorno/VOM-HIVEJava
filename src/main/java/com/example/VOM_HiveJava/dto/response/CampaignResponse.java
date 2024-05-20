package com.example.VOM_HiveJava.dto.response;

import com.example.VOM_HiveJava.entity.Company;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CampaignResponse(

        Long idCampaign,
        String nmCampaign,
        String target,
        byte[] details,
        String status,
        LocalDate dtRegister,
        CompanyResponse company,
        ProductResponse product
) {
}
