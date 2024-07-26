package vip.example.qrbasedcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.example.qrbasedcatalog.model.Product;
import vip.example.qrbasedcatalog.model.Website;
import vip.example.qrbasedcatalog.service.ProductService;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    ProductService productService;
    @GetMapping("/getProduct/{name}")
    public String createProduct(@PathVariable String name, Model model) {
        Product product = productService.getProduct(name);
        Website website = productService.getWebsite();
        if (website!=null){
            product.setUrl(website.getUrl());
        }
        model.addAttribute("product", product);
        return "/product.jsp";
    }
}
