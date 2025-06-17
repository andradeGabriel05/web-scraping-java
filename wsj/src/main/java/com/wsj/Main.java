package com.wsj;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String url = "https://docs.onicloud.io/index.html";

        try {
            Document document = Jsoup.connect(url).get();

            Elements pTags = document.select("p");

            for (Element pTag : pTags) {
                System.out.println(pTag.text());
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}