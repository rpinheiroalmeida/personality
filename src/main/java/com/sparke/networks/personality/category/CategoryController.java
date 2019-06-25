package com.sparke.networks.personality.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Workd";
    }
}
