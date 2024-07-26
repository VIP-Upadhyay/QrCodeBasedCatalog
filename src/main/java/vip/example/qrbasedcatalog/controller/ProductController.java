package vip.example.qrbasedcatalog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.example.qrbasedcatalog.model.Product;
import vip.example.qrbasedcatalog.model.Website;
import vip.example.qrbasedcatalog.service.ProductService;

@Controller
    @RequestMapping("admin/api/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public String createProduct(
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video, Model model) {

        try {
            Product product = productService.createProduct(name, image, video);
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error","Product name is already exists");
            return  "/addProduct.jsp";
        }
        return "redirect:/admin";
    }

    @GetMapping("/getProduct/{name}")
    public String createProduct(@PathVariable String name,Model model) {
        Product product = productService.getProduct(name);
        Website website = productService.getWebsite();
        if (website!=null){
            product.setUrl(website.getUrl());
        }
        model.addAttribute("product", product);
        return "/product.jsp";
    }

    @GetMapping("/delete/{name}")
    public String deleteProduct(@PathVariable String name, Model model) {
        try {
            productService.deleteProduct(name);
            return "redirect:/admin";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to delete product: " + e.getMessage());
            return "/error.jsp";
        }
    }

    @PostMapping("/edit/{name}")
    public String editProduct(
            @PathVariable String name,
            @RequestParam(required = false) String newName,
            @RequestParam(required = false) MultipartFile newImage,
            @RequestParam(required = false) MultipartFile newVideo,
            Model model) {
        try {
            Product updatedProduct = productService.updateProduct(name, newName, newImage, newVideo);
            if (updatedProduct == null) {
                model.addAttribute("error", "Product not found");
                return "/error.jsp";
            }
            return "redirect:/admin";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update product: " + e.getMessage());
            return "/error.jsp";
        }
    }

    @PostMapping("/addWebsite")
    public String editweb(
            @RequestParam(required = true) String url,
            Model model) {
        try {
            Website updatedProduct = productService.addUpdateWeb(url);
            if (updatedProduct == null) {
                model.addAttribute("error", "Website not found");
                return "/error.jsp";
            }
            return "redirect:/admin";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update product: " + e.getMessage());
            return "/error.jsp";
        }
    }
}
