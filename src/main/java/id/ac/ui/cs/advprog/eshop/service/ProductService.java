package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product create(Product product);

    public Product edit(Product editedProduct);

    public Optional<Product> findById(String idProduct);
    public List<Product> findAll();

    public Product deleteProduct(String idProduct);

}
