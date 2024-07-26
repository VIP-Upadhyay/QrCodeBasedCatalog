package vip.example.qrbasedcatalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
    @GetMapping("/admin")
    public String showHomePage(
            @RequestParam(value = "continue",required = false) String cont
    ) {
        return "/abc.jsp";
    }

}
