package com.example.VOM_HiveJava.dto.request;

import com.example.VOM_HiveJava.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(

        @Size(min = 5, max = 255)
        @NotNull(message = "Nome do produto é obrigatório")
        String nmProduct,


        @Size(min = 5, max = 100)
        @NotNull(message = "Qual a categoria do produto?")
        Category category
) {
}
