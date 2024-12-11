package googledemo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

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
    public String search(
            @RequestParam String keyword,
            @RequestParam(required = false) String category,
            Model model) {
        try {
            // 初始化 GoogleQuery 並執行查詢
            GoogleQuery googleQuery = new GoogleQuery(keyword);
            HashMap<String, String> results = googleQuery.query();

            // 如果有選擇類別，篩選結果
            if (category != null && !category.isEmpty()) {
                results = results.entrySet().stream()
                        .filter(entry -> entry.getKey().toLowerCase().contains(category.toLowerCase()))
                        .collect(Collectors.toMap(
                                entry -> entry.getKey(),
                                entry -> entry.getValue(),
                                (e1, e2) -> e1,
                                HashMap::new
                        ));
            }

            // 將結果和類別添加到模型中
            model.addAttribute("results", results);
            model.addAttribute("keyword", keyword);
            model.addAttribute("category", category);

            // 除錯輸出
            System.out.println("Keyword: " + keyword);
            System.out.println("Category: " + category);

            return "result"; // 返回 result.html

        } catch (IOException e) {
            // 當發生錯誤時，顯示錯誤訊息
            model.addAttribute("error", "搜索時發生錯誤: " + e.getMessage());
            return "error";
        }
    }
}
