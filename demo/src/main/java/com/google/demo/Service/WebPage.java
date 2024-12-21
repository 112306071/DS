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
	}
	public void setScore(ArrayList<Keyword> keywordList) throws IOException{
		score = 0;
		for (Keyword k: keywordList) {
			try{
				score += counter.countKeyword(k.name) * k.weight;
			}catch(IOException e){
				System.out.println("Error processing keyword: " + k.name);
			}
		}
	}
	public double getScore() {
		return score;
	}
}
