package com.google.demo.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class GoogleService {

	
	public HashMap<String, String> query(String keyword) throws IOException {
		HashMap<String, String> results = new HashMap<>(); 

		System.out.println("Keyword: " + keyword);

		String url = "https://www.google.com/search?q=" + keyword.replaceAll(" ", "+");
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();

		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		System.out.println("Fetched " + lis.size() + " results");

		for (Element li : lis) {
			try {
				Elements links = li.select("a");
				if (links.isEmpty()) {
					continue;
				}

				String citeUrl = links.get(0).attr("href").replace("/url?q=", "").split("&")[0];
				String decodedUrl = URLDecoder.decode(citeUrl, StandardCharsets.UTF_8);

				String title = li.select(".vvjwJb").text();
				if (title.isEmpty()) {
					continue;
				}

				
				results.put(title, decodedUrl);

				System.out.println("Title: " + title);
				System.out.println("URL: " + decodedUrl);
				System.out.println("---------------");

			} catch (Exception e) {
				System.out.println("Error parsing result: " + e.getMessage());
				e.printStackTrace();
			}
		}

		return results; 
	}
}
