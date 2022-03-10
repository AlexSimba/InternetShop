package com.alexsimba.attestation.controller;

import com.alexsimba.attestation.dto.ProductDTO;
import com.alexsimba.attestation.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class Controller {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/by-name")
    public String getPriseAndNumberByName(@RequestParam("product") String product) {
        return productsService.getPriseAndNumberByName(product);
    }

    @GetMapping("/all")
    public String getAll() {
        return productsService.getAll();
    }

    @PostMapping
    public String create(@RequestBody @Valid ProductDTO productDTO) {
        return productsService.create(productDTO);
    }

    @DeleteMapping
    public String delete(@RequestBody @Valid ProductDTO productDTO) {
        return productsService.delete(productDTO);
    }

}
