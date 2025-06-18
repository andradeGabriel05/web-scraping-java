package com.wsj;

import com.wsj.services.HandleFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        Scanner scanner = new Scanner(System.in);

        List<String> urls = new ArrayList<>();

        String outro = "y";
        while (outro.equals("y")) {
            System.out.println("Url?");
            urls.add(scanner.next());

            System.out.println("Outro? y/n");
            outro = scanner.next();
        }


        for (String url : urls) {
            try {
                Document document = Jsoup.connect(url).get();
                Element itemlist = document.select("article.md-content__inner.md-typeset").first();
                // select each li element
                Elements pTags = itemlist.select("ul");
                logger.info("Found {} <ul> tags.", pTags.size());

                String filename = url.substring(url.lastIndexOf("/") + 1).split(".html")[0];
                logger.info("Extracted filename from URL: {}", url);

                HandleFile handleFile = new HandleFile();
                logger.info("Creating file with name: {}", filename);
                Boolean fileCreated = handleFile.createFile(filename);

                for (Element pTag : pTags) {
                    if (fileCreated) handleFile.writeFile(filename, pTag.text());
                }
                logger.info("Successfully wrote to the file;");

            } catch (IOException e) {
                logger.error("An error occurred. {}", url, e);
                e.printStackTrace();
            }
        }
    }
}