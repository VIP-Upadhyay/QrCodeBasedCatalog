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
@RequestMapping("/admin")
public class ViewController {

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
//        model.addAttribute("error","Product name is already exists");
        return "/addProduct.jsp"; // This will resolve to the template name
    }


    @GetMapping("/")
    public String showHomePagxe() {
        return "/abc.jsp";
    }


    @Autowired
    ProductService productService;
    @GetMapping("/edit/{name}")
    public String showEditForm(@PathVariable String name, Model model) {
        Product product = productService.getProduct(name);
        System.out.println(product);
        if (product == null) {
            model.addAttribute("error", "Product not found");
            return "/error.jsp";
        }
        model.addAttribute("product", product);
        return "/editProduct.jsp";
    }

    @GetMapping("/addWebsite")
    public String showEditWebsite(Model model) {
        Website website = productService.getWebsite();
        if (website != null) {
            model.addAttribute("website", website);
        }
        return "/addWebsite.jsp";
    }

}
