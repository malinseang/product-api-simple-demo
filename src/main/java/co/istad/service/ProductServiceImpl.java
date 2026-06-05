package co.istad.service;

import co.istad.dto.ProductRequest;
import co.istad.dto.ProductResponse;
import co.istad.dto.UpdateProductRequest;
import co.istad.entity.Product;
import co.istad.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private Integer nextId = 1004;

    private Product mapToEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        return product;
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        var product = mapToEntity(request);
        product.setUserId(1);
        product.setId(nextId++);
        return mapToResponse(productRepository.createProduct(product));
    }

    @Override
    public List<ProductResponse> findAllProducts(Integer id) {
        return List.of();
    }

    // FIX: removed unused Integer id parameter
    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.getProductList()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        var product = productRepository.findProductById(id);
        return mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest request) {
        var existingProduct = productRepository.findProductById(id);
        if (request.name() != null)
            existingProduct.setName(request.name());
        if (request.description() != null)
            existingProduct.setDescription(request.description());
        if (request.price() != null)
            existingProduct.setPrice(request.price());
        productRepository.updateProduct(existingProduct);
        return mapToResponse(existingProduct);
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        productRepository.findProductById(id); // throws 404 if not found
        return productRepository.deleteProductById(id);
    }
}