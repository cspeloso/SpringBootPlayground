package com.chrispeloso.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //  @Controller - Manages web requests via Spring
public class HomeController {

    //  this value will be injected at runtime from application.properties into appName
    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/")
    public String index() {
        System.out.println("App Name: " + appName);
        return "index.html";
    }

    @RequestMapping("/about")
    public String about() {
        return "about.html";
    }


}
