package com.example.VOM_HiveJava.dto.response;

import com.example.VOM_HiveJava.entity.Category;
import lombok.Builder;

@Builder
public record ProductResponse(

        Long id_product,
        String nm_product,
        Category category
) {
}
