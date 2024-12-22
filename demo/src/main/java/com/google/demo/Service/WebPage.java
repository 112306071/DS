package com.google.demo.Service;

import java.io.IOException;
import java.util.ArrayList;

public class WebPage{
	public String name;
	public String url;
	public WordCounter counter;
	public double score;

	public WebPage(String name, String url){
		this.name = name;
		this.url = url;
		this.counter = new WordCounter(url);
		score = 0;
	}
	public void setScore(ArrayList<Keyword> keywordList) throws IOException{
		for (Keyword k: keywordList) {
			score += counter.countKeyword(k.name) * k.weight;
		}
		score += counter.countWebsite() * 40;
	}
	public double getScore() {
		return score;
	}
}
