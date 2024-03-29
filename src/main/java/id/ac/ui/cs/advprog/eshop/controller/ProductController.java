package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Optional<Product> product = service.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "editProduct";
        } else {
            return "redirect:/product/list";
        }
    }

    @PutMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, @ModelAttribute Product product, Model model) {
        product.setProductId(id);
        service.edit(product);
        return "redirect:list";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteProduct( @PathVariable String id, Model model){
        service.deleteProduct(id);
        return "redirect:../list";

    }

}