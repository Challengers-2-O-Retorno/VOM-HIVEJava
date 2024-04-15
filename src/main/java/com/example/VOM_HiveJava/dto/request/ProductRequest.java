package com.example.VOM_HiveJava.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(

        @NotNull(message = "Nome do produto é obrigatório")
        String nm_product,

        String category
) {
}
