package com.google.demo.Service;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.net.URL;
 import java.net.URLConnection;
 import java.util.HashMap;

 import org.jsoup.Jsoup;
 import org.jsoup.nodes.Document;
 import org.jsoup.nodes.Element;
 import org.jsoup.select.Elements;

 public class GoogleService 
 {
     public String searchKeyword;
     public String url;
     public String content;
     public KeywordList kl;
     public GoogleService(String filter, String searchKeyword)
     {
         this.searchKeyword = searchKeyword;
         try 
         {
             // This part has been specially handled for Chinese keyword processing. 
             // You can comment out the following two lines 
             // and use the line of code in the lower section. 
             // Also, consider why the results might be incorrect 
             // when entering Chinese keywords.
        	 kl = new KeywordList(filter,searchKeyword);
             String encodeKeyword=java.net.URLEncoder.encode(searchKeyword,"utf-8");
             this.url = "https://www.google.com/search?q="+encodeKeyword+"&oe=utf8&num=20";
             
             // this.url = "https://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";
         }
         catch (Exception e)
         {
             System.out.println(e.getMessage());
         }
     }

     private String fetchContent() throws IOException
     {
         String retVal = "";
         URL u = new URL(url);
         URLConnection conn = u.openConnection();
         //set HTTP header
         conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
         InputStream in = conn.getInputStream();
         InputStreamReader inReader = new InputStreamReader(in, "utf-8");
         BufferedReader bufReader = new BufferedReader(inReader);
         String line = null;

         while((line = bufReader.readLine()) != null)
         {
             retVal += line;
         }
         return retVal;
     }

     public HashMap<String, String> query() throws IOException
     {
         if(content == null)
         {
             content = fetchContent();
         }

         HashMap<String, String> retVal = new HashMap<String, String>();
         PageHeap heap = new PageHeap();
         /* 
          * some Jsoup source
          * https://jsoup.org/apidocs/org/jsoup/nodes/package-summary.html
          * https://www.1ju.org/jsoup/jsoup-quick-start
          */

         //using Jsoup analyze html string
         Document doc = Jsoup.parse(content);

         //select particular element(tag) which you want 
         Elements lis = doc.select("div");
         lis = lis.select(".kCrYT");
         
         for(Element li : lis)
         {
             try 
             {
            	 Elements links = li.select("a");
                 if (links.isEmpty()) {
                     continue;  // 跳過沒有 <a> 的元素
                 }
                 String citeUrl = li.select("a").get(0).attr("href").replace("/url?q=", "");
                 String title = li.select("a").get(0).select(".vvjwJb").text();

                 if(title.equals("")) 
                 {
                     continue;
                 }

                 System.out.println("Title: " + title + " , url: " + citeUrl);

                 WebPage wp = new WebPage (title, citeUrl);
                 wp.setScore(kl.getList());
                 heap.add(wp);
                 
             } catch (IndexOutOfBoundsException e) 
             {
 				e.printStackTrace();
             }
         }
        while (!heap.getHeap().isEmpty()){
        	WebPage wp = heap.poll();
        	retVal.put(wp.name, wp.url);
         }
         return retVal;
     }
 }