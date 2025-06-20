# Web Scraping com Jsoup (Java)

Este projeto realiza web scraping utilizando a biblioteca **Jsoup** para extrair conteúdo textual de páginas web e salvar os dados em arquivos `.txt`. É uma aplicação simples de linha de comando, com foco em praticidade e reutilização.

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Jsoup** (`org.jsoup`)
- **SLF4J** (`org.slf4j`)
- **Maven** ou qualquer gerenciador de dependência Java
- Biblioteca personalizada `HandleFile` (gerencia criação e escrita de arquivos)

  ## 🚀 Como funciona

1. O usuário é solicitado a digitar o nome de um arquivo.
2. Após a criação do arquivo (caso ainda não exista),  o usuário insere a URL a ser processada.
3. O programa conecta-se à URL usando um `userAgent` customizado.
4. O conteúdo é extraído de elementos como `<p>`, `<h1-h4>`, `<li>` e parágrafos dentro de blocos `div.tabbed-block`.
5. Os textos extraídos são salvos no arquivo especificado.
6. O processo repete até que o usuário envie uma entrada vazia.

## 📦 Instalação

Clone o repositório:
bash
- git clone https://github.com/andradeGabriel05/web-scraping-java.git
- cd web-scraping-java
- cd wsj

## ✅ Execução 
O projeto é compatível com as principais IDEs Java:
- IntelliJ IDEA
- Eclipse
- Visual Studio Code (com extensão Java)

## ⚠️ Atenção
Clone ou baixe o repositório.
Abra a pasta src/ como projeto na sua IDE.
Certifique-se de que a classe Main.java está definida como classe de entrada.
Clique com o botão direito sobre Main.java e selecione "Run" (ou use o atalho Shift + F10 no IntelliJ / Ctrl + F11 no Eclipse).

