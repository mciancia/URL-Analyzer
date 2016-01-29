package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.net.URL;


/**
 * Created by mateusz on 28.01.2016.
 */
public class WebAnalyzer {
    private String url;
    private Set<String> links = new HashSet<>();
    private int totalImagesNum = 0;
    private int totalImagesSize = 0;

    public WebAnalyzer(String url) throws IOException{
        this.url = url;
        Document document = Jsoup.connect(url).get();
        try {
            Set<String> imagess = new HashSet<>();
            document.select("img").stream().map(e -> e.attr("abs:src")).filter(e -> !imagess.contains(e)).forEach(e -> {
                imagess.add(e);
                totalImagesNum++;
            });
            for(String s : imagess){
                this.totalImagesSize+= getSize(s);
            }
        } catch (Exception e){

        }
        document.select("a").forEach(e -> this.links.add(e.attr("abs:href")));
    }
    private static int getSize(String url) throws IOException {
        URLConnection connection = (new URL(url)).openConnection();
        int length = connection.getContentLength();
        if(length < 0) {
            throw new IOException();
        }
        return length;
    }

    public Set<String> getLinks() {
        return links;
    }

    public int getTotalImagesNum() {
        return totalImagesNum;
    }

    public int getTotalImagesSize() {
        return totalImagesSize;
    }
}
