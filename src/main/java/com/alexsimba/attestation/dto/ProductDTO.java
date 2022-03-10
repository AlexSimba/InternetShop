package com.alexsimba.attestation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank
    private String nameProduct;

    @NotNull
    @Min(0)
    double prise;

    @NotNull
    @Min(0)
    int numberOfProduct;
}

