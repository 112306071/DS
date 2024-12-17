package com.google.demo.Controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.demo.Service.GoogleService;

@Controller
public class GoogleController {

    @GetMapping("/")
    public String showSearchPage() {
        return "google"; 
    }

    @GetMapping("/google")
    public String search(@RequestParam String keyword, Model model) {
        try {
            GoogleService googleQuery = new GoogleService(keyword);
            HashMap<String, String> results = googleQuery.query();
            model.addAttribute("google", results);
            model.addAttribute("keyword", keyword);
            return "result"; // 對應到 results.html
        } catch (IOException e) {
            model.addAttribute("error", "搜索時發生錯誤: " + e.getMessage());
            return "error";
        }
    }
}