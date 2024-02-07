package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Optional<Product> findById(String idProduct) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(idProduct))
                .findFirst();
    }

    public Product edit(Product editedProduct) {
        Optional<Product> checkProduct = findById(editedProduct.getProductId());
        if (checkProduct.isPresent()) {
            int index = productData.indexOf(checkProduct.get());
            productData.set(index, editedProduct);
            return editedProduct;
        } else {
            // tidak ditemukan
            throw new IllegalArgumentException("Product not found with ID: " + editedProduct.getProductId());
        }
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }


    public Product deleteProduct(String idProduct) {
        Optional<Product> product = findById(idProduct);
        if (product.isPresent()) {
            productData.remove(product.get());
            return product.get();
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + idProduct);
        }
    }

}
