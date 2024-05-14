package com.example.VOM_HiveJava.dto.response;

import com.example.VOM_HiveJava.entity.Company;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CampaignResponse(

        Long id_campaign,
        String nm_campaign,
        String target,
        byte[] details,
        String status,
        LocalDate dt_register,
        CompanyResponse company,
        ProductResponse product
) {
}
