package co.istad.service;

import co.istad.dto.ProductRequest;
import co.istad.dto.ProductResponse;
import co.istad.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest product);

    List<ProductResponse> findAllProducts(Integer id);
    ProductResponse findProductById(Integer id);

    ProductResponse updateProduct(Integer id, UpdateProductRequest request);

    boolean deleteProduct(int id);

    boolean deleteProduct(Integer id);

    List<ProductResponse> findAllProducts();
}