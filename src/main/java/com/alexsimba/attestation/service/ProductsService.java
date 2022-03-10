package com.alexsimba.attestation.service;

import com.alexsimba.attestation.dto.ProductDTO;
import com.alexsimba.attestation.model.Products;
import com.alexsimba.attestation.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public String getPriseAndNumberByName(String name) {
        Products products = productsRepository.getProductsByNameProduct(name);
        return String.format("%s стоит %f. В наличии %d штук ", products.getNameProduct(), products.getPrise(), products.getNumberOfProduct());
    }

    public String create(ProductDTO productDTO) {
        Products products = productsRepository.getProductsByNameProductAndPrise(productDTO.getNameProduct(), productDTO.getPrise());
        if (products == null) {
            productsRepository.save(new Products(productDTO.getNameProduct(), productDTO.getPrise(), productDTO.getNumberOfProduct()));
        } else {
            products.setNumberOfProduct(products.getNumberOfProduct() + productDTO.getNumberOfProduct());
            productsRepository.save(products);
        }
        return "Товар добавлен";
    }

    public String delete(ProductDTO productDTO) {
        Products products = productsRepository.getProductsByNameProductAndPrise(productDTO.getNameProduct(), productDTO.getPrise());

        if (products != null) {
            if (productDTO.getNumberOfProduct() < products.getNumberOfProduct()) {
                products.setNumberOfProduct(products.getNumberOfProduct() - productDTO.getNumberOfProduct());
                productsRepository.save(products);
                return "Товар продан";
            } else if (productDTO.getNumberOfProduct() > products.getNumberOfProduct()) {
                return "Товара не хватает";
            } else {
                productsRepository.delete(products);
                return "Товара продан и больше нет на складе";
            }
        }
        throw new EntityNotFoundException();
    }

    public String getAll() {
        List<Products> allProducts = productsRepository.findAll();
        String string = new String();
        for (Products products: allProducts) {
            string += String.format("%s стоит %.2f. В наличии %d штук %n", products.getNameProduct(), products.getPrise(), products.getNumberOfProduct());
        }
        return string;
    }

}
