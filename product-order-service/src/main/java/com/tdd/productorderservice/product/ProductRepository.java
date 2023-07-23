package com.tdd.productorderservice.product;

import java.util.HashMap;
import java.util.Map;

class ProductRepository {
    private Long sequence = 0L;
    private Map<Long, Product> persistence = new HashMap<>();

    public void save(Product product) {
        product.assingId(++sequence);
        persistence.put(product.getId(), product);
    }
}
