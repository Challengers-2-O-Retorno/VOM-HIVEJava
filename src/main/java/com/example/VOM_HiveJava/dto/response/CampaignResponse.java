package com.example.VOM_HiveJava.dto.response;

import com.example.VOM_HiveJava.entity.Company;

import java.time.LocalDate;

public record CampaignResponse(

        int id_campaign,
        String nm_campaign,
        String target,
        byte[] details,
        String status,
        LocalDate dt_register,
        CompanyResponse company,
        ProductResponse product
) {
}
