package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductService productService;

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"));
    }

    @Test
    public void testListProductPage() throws Exception{
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"));
    }

    @Test
    public void testEditProductPage() throws Exception{

        String idProduct = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product mockProduct = new Product();
        mockProduct.setProductId(idProduct);
        when(productService.findById(idProduct)).thenReturn(Optional.of(mockProduct));

        mockMvc.perform(get("/product/edit/{id}", idProduct))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", mockProduct));
    }

    @Test
    void testEditProductPageIfIdInvalid() throws Exception{
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(10);

        when(productService.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/product/edit/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void testEditProductPut() throws Exception{
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(10);

        when(productService.edit(product)).thenReturn(product);

        mockMvc.perform(put("/product/edit/{id}", id)
                        .param("productId", id)
                        .param("productName", product.getProductName())
                        .param("productQuantity", String.valueOf(product.getProductQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

    }

    @Test
    void testCreateProductPost() throws Exception{
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Usep");
        product.setProductQuantity(100);

        when(productService.create(product)).thenReturn(product);

        mockMvc.perform(post("/product/create")
                        .param("productId", product.getProductId())
                        .param("productName", product.getProductName())
                        .param("productQuantity", String.valueOf(product.getProductQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

    }

    @Test
    void testDeleteProductDelete() throws Exception{

        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        mockMvc.perform(delete("/product/delete/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../list"));

        verify(productService).deleteProduct(productId);
    }
}
