package com.wsj;

import com.wsj.services.HandleFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        String url = "https://docs.onicloud.io/index.html";

        try {
            String filename = url.substring(url.lastIndexOf("/") + 1).split(".html")[0];
            logger.info("Extracted filename from URL: {}", filename);

            Document document = Jsoup.connect(url).get();
            Elements pTags = document.select("p");
            logger.info("Found {} <p> tags.", pTags.size());

            HandleFile handleFile = new HandleFile();
            logger.info("Creating file with name: {}", filename);
            handleFile.createFile(filename);

            for (Element pTag : pTags) {
                System.out.println(pTag.text());
                handleFile.writeFile(filename, pTag.text());
            }

        } catch (IOException e) {
            logger.error("An error occurred. {}", url, e);
            e.printStackTrace();
        }

    }
}