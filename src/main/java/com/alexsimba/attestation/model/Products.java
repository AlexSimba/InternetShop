package com.alexsimba.attestation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "product_name")
    private String nameProduct;

    @NotNull
    @Min(0)
    @Column(name = "prise")
    private double prise;

    @NotNull
    @Min(0)
    @Column(name = "number_of_product")
    private int numberOfProduct;

    public Products(@NotBlank String nameProduct, @NotNull @Min(0) double prise, @NotNull @Min(0) int numberOfProduct) {
        this.nameProduct = nameProduct;
        this.prise = prise;
        this.numberOfProduct = numberOfProduct;
    }
}
