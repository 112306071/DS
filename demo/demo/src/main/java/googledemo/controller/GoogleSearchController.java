package googledemo.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import googledemo.service.GoogleQuery;

@Controller
public class GoogleSearchController {

    @GetMapping("/")
    public String showSearchPage() {
        return "search"; // 對應到 search.html
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        try {
            GoogleQuery googleQuery = new GoogleQuery(keyword);
            HashMap<String, String> results = googleQuery.query();
            model.addAttribute("results", results);
            model.addAttribute("keyword", keyword);
            return "results"; // 對應到 results.html
        } catch (IOException e) {
            model.addAttribute("error", "搜索時發生錯誤: " + e.getMessage());
            return "error";
        }
    }
}
