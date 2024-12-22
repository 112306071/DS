package com.google.demo.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter
{
    private String urlStr;
    private String content;
    private static final String[] website = {
        "https://www.gamer.com.tw/",
        "https://www.op.gg/",
        "https://www.4gamers.com.tw/",
        "https://news.gamebase.com.tw/",
        "https://www.ign.com/",
        "https://www.gamespot.com/",
        "https://playmeow.com/"
    };

    public WordCounter(String urlStr)
    {
        this.urlStr = urlStr;
    }

    private String fetchContent() throws IOException
    {
        URL url = new URL(this.urlStr);
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuilder retVal = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null)
        {
            retVal.append(line).append("\n");
        }

        return retVal.toString();
    }

    public int countKeyword(String keyword) throws IOException
    {
        if (content == null)
        {
            content = fetchContent();
        }

        content = content.toUpperCase();
        keyword = keyword.toUpperCase();

        int retVal = 0;
        int fromIdx = 0;
        int found;

        while ((found = content.indexOf(keyword, fromIdx)) != -1)
        {
            retVal++;
            fromIdx = found + keyword.length();
        }
        
        return retVal;
    }

    public int countWebsite() throws IOException
    {
        return countMultipleWebsites(website);
    }

    public int countMultipleWebsites(String[] keywords) throws IOException
    {
        if (content == null)
        {
            content = fetchContent();
        }

        content = content.toUpperCase();
        int totalCount = 0;

        for (String keyword : keywords)
        {
            String upperKeyword = keyword.toUpperCase();
            int fromIdx = 0;
            int found;

            while ((found = content.indexOf(upperKeyword, fromIdx)) != -1)
            {
                totalCount++;
                fromIdx = found + upperKeyword.length();
            }
        }

        return totalCount;
    }
}
