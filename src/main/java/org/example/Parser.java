package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private final ArrayList<String> parserList;
    Parser() {
        parserList = new ArrayList<>();
    }
    ArrayList<String> getParserList(String url) {
        try {
            //Получаем HTML-код страницы
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 OPR/99.0.0.0 (Edition Yx 05)").get();

            Elements thread = doc.select("tr > td > span.station");
            Elements time = doc.select("tr > td > span.time");
            for (int i = 0; i < thread.size(); i = i+2) {
                parserList.add(thread.get(i).text() + " --> " + thread.get(i+1).text());
                parserList.add("  Время отправления: " + time.get(i).text() + "  Время прибытия: " + time.get(i+1).text() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (parserList.size() == 0){
            parserList.add("Упс.Расписание не найдено");
        }
        return parserList;
    }
}
