package com.wsj;

import com.wsj.services.HandleFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Scanner;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HandleFile handleFile = new HandleFile();
        String url;
        String filename = "";

        while (true) {
            System.out.println("Pressione ENTER sem digitar nada para finalizar.");
            Boolean fileCreated = false;
            while (!fileCreated) {
                System.out.print("Digite o nome do arquivo: ");
                filename = scanner.nextLine();
                if (filename.isBlank()) {
                    logger.info("Criação de arquivo interrompido.");
                    return;
                }
                fileCreated = handleFile.createFile(filename);
            }

            System.out.print("Digite a URL: ");
            url = scanner.nextLine();
            if (url.isBlank()) {
                logger.info("Entrada de URLs interrompida.");
                break;
            }
//            logger.debug("URL adicionada à lista: {}", url);


            try {
                // O userAgent evita bloqueios por sites que barram bots
                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
                                + " AppleWebKit/537.36 (KHTML, like Gecko)"
                                + " Chrome/113.0.0.0 Safari/537.36")
                        .timeout(10 * 1000)
                        .get();

                Element itemlist = doc
                        .select("article.md-content__inner.md-typeset")
                        .first();

                Elements paragrafos = itemlist.select("p, h3, h4, li, h1, div.tabbed-block p");

                if (paragrafos.isEmpty()) {
                    logger.warn("Nenhum parágrafo encontrado em: {}", url);
                } else {
                    for (Element p : paragrafos) {
                    String texto = "";
                        if (p.tagName().equals("li")) {
                            Element cloneLi = p.clone();

                            cloneLi.select("ul").remove();
                            cloneLi.select("p").remove();

                            texto = cloneLi.text().trim();

                        } else {
                            texto = p.text();
                        }

                        handleFile.writeFile(filename, texto);
                    }
                    logger.info("Parágrafos extraídos e salvos da URL: {}", url);
                }
            } catch (IOException e) {
                logger.error("Erro ao acessar a URL: {}", url);
            }
        }
        logger.info("Fim da execução.");
        scanner.close();
    }
}