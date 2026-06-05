package co.istad.repository;

import co.istad.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {

    private final List<Product> productList = new ArrayList<>() {{
        add(new Product(1001, "Cocacola", "Nice with ice", 1.12f, 3));
        add(new Product(1002, "Cake", "Nice with ice", 1.00f, 4));
        add(new Product(1003, "Candy", "Nice with ice", 0.25f, 5));
    }};

    public List<Product> getProductList() {
        return productList;
    }

    public Product createProduct(Product product) {
        productList.add(product);
        return product;
    }

    public Product findProductById(Integer id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product with ID=" + id + " Not Found"));
    }

    public Product updateProduct(Product updateProduct) {
        for (int i = 0; i < productList.size(); i++) {
            var product = productList.get(i);
            if (product.getId().equals(updateProduct.getId())) {
                productList.set(i, updateProduct);
                return updateProduct;
            }
        }
        return null;
    }

    public boolean deleteProductById(Integer id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }
}