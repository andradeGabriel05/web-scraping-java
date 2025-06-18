package com.wsj;

import com.wsj.services.HandleFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HandleFile handleFile = new HandleFile();

        System.out.print("Digite o nome do arquivo: ");
        String filename = scanner.nextLine();

        handleFile.createFile(filename);

        List<String> urls = new ArrayList<>();
        String url;

        System.out.println("Digite as URLs uma por linha. Pressione ENTER sem digitar nada para finalizar.");

        while (true) {
            System.out.print("URL: ");
            url = scanner.nextLine();
            if (url.isBlank()) break;
            urls.add(url);
        }
        for (String u : urls) {
            try {
                // O userAgent evita bloqueios por sites que barram bots
                Document doc = Jsoup.connect(u)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36")
                        .timeout(10 * 1000)
                        .get();

                Element itemlist = doc.select("article.md-content__inner.md-typeset").first();
                Elements paragrafos = itemlist.select("p, h3, h4, ul, li, h1");

                if (paragrafos.isEmpty()) {
                    handleFile.writeFile(filename, "Nenhum parágrafo encontrado em: " + u);
                } else {
                    for (Element p : paragrafos) {
                        String texto = p.text();
                        handleFile.writeFile(filename, texto);
                    }
                }

                System.out.println("Parágrafos extraídos da URL: " + u);

                } catch (IOException e) {
                    System.err.println("Erro ao acessar a URL: " + u);
                    handleFile.writeFile(filename, "Erro ao acessar a URL: " + u);
                }
        }

        scanner.close();
        System.out.println("Fim da execução.");        }
    }
}