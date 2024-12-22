package com.google.demo.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebTree {
    private WebPage root;
    private List<WebTree> children;

    public WebTree(WebPage root) {
        this.root = root;
        this.children = new ArrayList<>();
    }

    public void addChild(WebTree child) {
        children.add(child);
    }

    
    public void calculateTotalScore(ArrayList<Keyword> keywordList) throws IOException {
        root.setScore(keywordList);

        for (WebTree child : children) {
            child.calculateTotalScore(keywordList);
        }

        for (WebTree child : children) {
            root.score += child.root.getScore();  
        }
    }

    public WebPage getRoot() {
        return root;
    }

    public List<WebTree> getChildren() {
        return children;
    }

}
