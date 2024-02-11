package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    Product initProduct(String idProduct, String productName, Integer quantity){
        Product product = new Product();
        product.setProductId(idProduct);
        product.setProductName(productName);
        product.setProductQuantity(quantity);
        return product;
    }

    @Test
    void testCreateProduct() {
        Product product = initProduct("eb558e9f-1c39-460e-8860-71af6af63bd6","Sampo Cap Bambang", 100);

        when(productRepository.create(product)).thenReturn(product);

        Product result = productServiceImpl.create(product);

        assertEquals(product.getProductId(), result.getProductId());
    }

    @Test
    void testFindId(){

        Product product = initProduct("eb558e9f-1c39-460e-8860-71af6af63bd6","Sampo Cap Bambang", 100);

        when(productRepository.create(product)).thenReturn(product);
        productServiceImpl.create(product);

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        Optional<Product> productFind = productServiceImpl.findById(product.getProductId());

        assertTrue(productFind.isPresent());
        assertEquals(product.getProductId(), productFind.get().getProductId());
        assertEquals(product.getProductName(), productFind.get().getProductName());
        assertEquals(product.getProductQuantity(), productFind.get().getProductQuantity());

    }

    @Test
    void testFindAll(){
        List<Product> expectedProducts = new ArrayList<>();

        Product product = initProduct("eb558e9f-1c39-460e-8860-71af6af63bd6","Sampo Cap Bambang", 100);
        Product product2 = initProduct("a0f9de46-90b1-437d-a0bf-d0821dde9096", "Sampo Cap Usep", 50);

        expectedProducts.add(product);
        expectedProducts.add(product2);

        Iterator<Product> productIterator = expectedProducts.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> actualProducts = productServiceImpl.findAll();

        assertEquals(expectedProducts, actualProducts);

    }

    @Test
    void testDelete(){

        Product product = initProduct("eb558e9f-1c39-460e-8860-71af6af63bd6","Sampo Cap Bambang", 100);

        when(productRepository.create(product)).thenReturn(product);
        productServiceImpl.create(product);

        when(productRepository.deleteProduct(product.getProductId())).thenReturn(product);
        productServiceImpl.deleteProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
        Optional<Product> productFind = productServiceImpl.findById(product.getProductId());

        assertTrue(productFind.isEmpty());
    }

    @Test
    void testEditProduct() {
        // Arrange
        Product product = initProduct("eb558e9f-1c39-460e-8860-71af6af63bd6","Sampo Cap Bambang", 100);

        when(productRepository.edit(product)).thenReturn(product);

        Product result = productServiceImpl.edit(product);

        assertEquals(product, result);
    }

}

