package org.example.services;

import org.example.model.Product;

public class PricingService {

    public static final double IVA = 1.21;

    public PricingService() {}

    public Product calculatePriceWithTaxes(Product product) {
        Double priceWithTaxes = product.getPrice() * IVA;
        return new Product(product.getName(),product.getPrice(), priceWithTaxes);
    }
}
