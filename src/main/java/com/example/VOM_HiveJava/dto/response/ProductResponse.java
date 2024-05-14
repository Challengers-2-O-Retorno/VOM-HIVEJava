package com.example.VOM_HiveJava.dto.response;

import lombok.Builder;

@Builder
public record ProductResponse(

        Long id_product,
        String nm_product,
        String category
) {
}
