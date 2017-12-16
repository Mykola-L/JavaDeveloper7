package ua.spring.controller;

import ua.spring.model.Product;
import ua.spring.services.ProductService;
import ua.spring.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private ProductValidator productValidator;

    @Autowired
    public ProductController(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute(productList);
        return "index";
    }

    @RequestMapping(value = "/product/add", method=RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("productForm", new Product());
        return "add_product";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("productForm") Product productForm,
                               BindingResult bindingResult, Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "add_product";
        }

        productService.add(productForm);

        return "redirect:/index";
    }

    @RequestMapping(value = "/product/{id}/delete", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/product/{id}/update", method = RequestMethod.GET)
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("productForm", product);
        return "update_product";
    }

    @RequestMapping(value = "/product/{id}/update", method = RequestMethod.POST)
    public String saveUpdatedProduct(@ModelAttribute("productForm") Product productForm,
                              BindingResult bindingResult, Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "update_product";
        }

        productService.update(productForm);

        return "redirect:/index";
    }

}
