package com.alexsimba.attestation.repository;

import com.alexsimba.attestation.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
    Products getProductsByNameProduct(@Param("nameProduct") String name);
    Products getProductsByNameProductAndPrise(@Param("nameProduct") String name, @Param("prise") double prise);
}
