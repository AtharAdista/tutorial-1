package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById(){
        Product productInit = new Product();
        productInit.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productInit.setProductName("Sampo Cap Bambang");
        productInit.setProductQuantity(100);
        productRepository.create(productInit);

        Optional<Product> productFind = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertTrue(productFind.isPresent());
        assertEquals(productFind.get(), productInit);
    }

    @Test
    void testFindByIdIsEmpty(){
        Optional<Product> productFind = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertFalse(productFind.isPresent());
    }

    @Test
    void testFindByIdIfFalse(){
        Product productInit = new Product();
        productInit.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productInit.setProductName("Sampo Cap Bambang");
        productInit.setProductQuantity(100);
        productRepository.create(productInit);

        Optional<Product> productFind = productRepository.findById("aaaaa-1c39-460e-8860-71af6af63bd6");
        assertTrue(productFind.isEmpty());
    }

    @Test
    void testFindIdfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Optional<Product> productFind1 = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertTrue(productFind1.isPresent());
        assertEquals(productFind1.get(), product1);
        Optional<Product> productFind2 = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertTrue(productFind2.isPresent());
        assertEquals(productFind2.get(), product2);
    }

    @Test
    void testEditProductName(){
        Product productInit = new Product();
        productInit.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productInit.setProductName("Sampo Cap Bambang");
        productInit.setProductQuantity(100);
        productRepository.create(productInit);

        Product productEdited = new Product();
        productEdited.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productEdited.setProductName("Sampo Cap ABC");
        productEdited.setProductQuantity(100);

        productRepository.edit(productEdited);

        Optional<Product> retrievedProduct = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Sampo Cap ABC", retrievedProduct.get().getProductName());
        assertEquals(100, retrievedProduct.get().getProductQuantity());

    }

    @Test
    void testEditProductQuantity(){
        Product productInit = new Product();
        productInit.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productInit.setProductName("Sampo Cap Bambang");
        productInit.setProductQuantity(100);
        productRepository.create(productInit);

        Product productEdited = new Product();
        productEdited.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productEdited.setProductName("Sampo Cap Bambang");
        productEdited.setProductQuantity(200);

        productRepository.edit(productEdited);

        Optional<Product> retrievedProduct = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Sampo Cap Bambang", retrievedProduct.get().getProductName());
        assertEquals(200, retrievedProduct.get().getProductQuantity());

    }

    @Test
    void testEditProductNameAndQuantity(){
        Product productInit = new Product();
        productInit.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productInit.setProductName("Sampo Cap Bambang");
        productInit.setProductQuantity(100);
        productRepository.create(productInit);

        Product productEdited = new Product();
        productEdited.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productEdited.setProductName("Sampo Cap ABC");
        productEdited.setProductQuantity(200);

        productRepository.edit(productEdited);

        Optional<Product> retrievedProduct = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Sampo Cap ABC", retrievedProduct.get().getProductName());
        assertEquals(200, retrievedProduct.get().getProductQuantity());

    }

    @Test
    void testDeleteProduct(){
        Product productInit = new Product();
        productInit.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productInit.setProductName("Sampo Cap Bambang");
        productInit.setProductQuantity(100);
        productRepository.create(productInit);

        Product productDeleted = productRepository.deleteProduct("eb558e91f-1c39-460e-8860-71af6af63bd6");
        Optional<Product> retrievedProduct = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertFalse(retrievedProduct.isPresent());
        assertEquals(productDeleted.getProductId(), productInit.getProductId());

    }

    @Test
    void testDeleteProductIfIdFalse(){
        Product productInit = new Product();
        productInit.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        productInit.setProductName("Sampo Cap Bambang");
        productInit.setProductQuantity(100);
        productRepository.create(productInit);

        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.deleteProduct("aaaaaaaaa-1c39-460e-8860-71af6af63bd6");
        });
    }

    @Test
    void testDeleteProductIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e91f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("b0s1dw32-84b2-123s-c5ad-a2193adf1212");
        product3.setProductName("Sampo Cap Loman");
        product3.setProductQuantity(200);
        productRepository.create(product3);

        Product DeletedProduct1 = productRepository.deleteProduct("eb558e91f-1c39-460e-8860-71af6af63bd6");
        Optional<Product> productFind1 = productRepository.findById("eb558e91f-1c39-460e-8860-71af6af63bd6");
        assertTrue(productFind1.isEmpty());
        assertEquals(DeletedProduct1.getProductId(), product1.getProductId());
        assertEquals(DeletedProduct1.getProductName(), product1.getProductName());
        assertEquals(DeletedProduct1.getProductQuantity(), product1.getProductQuantity());

        Product DeletedProduct2 = productRepository.deleteProduct("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        Optional<Product> productFind2 = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertTrue(productFind2.isEmpty());
        assertEquals(DeletedProduct2.getProductId(), product2.getProductId());
        assertEquals(DeletedProduct2.getProductName(), product2.getProductName());
        assertEquals(DeletedProduct2.getProductQuantity(), product2.getProductQuantity());

        Optional<Product> productFind3 = productRepository.findById("b0s1dw32-84b2-123s-c5ad-a2193adf1212");
        assertTrue(productFind3.isPresent());
        assertEquals("b0s1dw32-84b2-123s-c5ad-a2193adf1212", product3.getProductId());
        assertEquals("Sampo Cap Loman", product3.getProductName());
        assertEquals(200, product3.getProductQuantity());

    }



}
