package com.LiveStats.LiveStats.controlles.views;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @Value("${app.logo-url}")
    private String logoUrl;

    @Value("${app.header-gradient}")
    private String headerGradient;

    @Value("${app.header-font-color}")
    private String headerFontColor;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        model.addAttribute("logoUrl", logoUrl);
        model.addAttribute("headerStyle", "background: " + headerGradient + ";");
        model.addAttribute("headerFontColor", "color: " + headerFontColor + ";");
        return "home";
    }
}
