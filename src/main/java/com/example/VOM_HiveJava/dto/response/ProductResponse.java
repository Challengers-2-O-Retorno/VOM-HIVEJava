package com.example.VOM_HiveJava.dto.response;

import com.example.VOM_HiveJava.entity.Category;
import lombok.Builder;

@Builder
public record ProductResponse(

        Long idProduct,
        String nmProduct,
        Category category
) {
}
