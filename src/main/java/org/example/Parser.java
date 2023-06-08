package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private final ArrayList<String> parserList;
    private String url;

    Parser() {
        parserList = new ArrayList<>();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    ArrayList<String> getParserList() {

        try {
            //Получаем HTML-код страницы
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 OPR/99.0.0.0 (Edition Yx 05)").get();

            Elements thread = doc.select("tr > td > span.station");
            Elements time = doc.select("tr > td > span.time");
            for (int i = 0; i < thread.size(); ++i) {
                parserList.add("Станция отправления: " + thread.get(i).text() + "  Время отправления: " + time.get(i).text());
                System.out.println("22");
                i = i + 1;
                parserList.add("Станция назначения: " + thread.get(i).text() + "  Время прибытия: " + time.get(1).text() + "\n");
                parserList.add("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parserList;
    }
}
