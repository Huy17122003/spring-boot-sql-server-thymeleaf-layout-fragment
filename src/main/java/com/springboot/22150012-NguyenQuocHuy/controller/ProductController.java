package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Product;
import com.springboot.dev_spring_boot_demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list-product")
    public String list(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/products/list-product";
    }

    @GetMapping("/product-form-insert")
    public String formInsert(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/products/product-form-insert";
    }

    @PostMapping("/save")
    public String save(Product product) {
        productService.save(product);
        return "redirect:/admin/products/list-product";
    }

    @GetMapping("/product-form-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/products/product-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        productService.deleteById(id);
        return "redirect:/admin/products/list-product";
    }
}
