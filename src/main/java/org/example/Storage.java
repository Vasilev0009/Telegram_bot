package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;

    Storage() {
        quoteList = new ArrayList<>();
        quoteList.add("Обожаю тебя, я пошел за пивом. Что тебе взять?");
        quoteList.add("Обожаю тебя, я пошел за пивом. Что тебе взять?");
        quoteList.add("Обожаю тебя, я пошел за пивом. Что тебе взять?");
    }

    String getRandQuote() {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int) (Math.random() * quoteList.size());
        //Из коллекции получаем цитату со случайным индексом и возвращаем ее
        return quoteList.get(randValue);
    }


            try {
                //Получаем HTML-код страницы
                Document doc = Jsoup.connect("http://расписание.рф/все/66/Екатеринбург/66/Каменск-Уральский/сегодня").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 OPR/99.0.0.0 (Edition Yx 05)").get();
//            System.out.println(doc);
                Elements thread = doc.select("tr > td > span.station");
//            System.out.println(thread.text());
                Elements time = doc.select("tr > td > span.time");
//            System.out.println(time.text());
                for(int i = 0; i< thread.size(); ++i) {

                    System.out.println("Станция отправления: " + thread.get(i).text() + "  Время отправления: " + time.get(i).text());
                    i = i+1;
                    System.out.println("Станция назначения: " + thread.get(i).text() + "  Время пребытия: " + time.get(1).text() +"\n");
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
//https://rasp.yandex.ru/search/?fromId=c11159&fromName=Шадринск&toId=c54&toName=Екатеринбург&when=7+июня
// https://rasp.yandex.ru/search/?fromName=%D0%A8%D0%B0%D0%B4%D1%80%D0%B8%D0%BD%D1%81%D0%BA&toId=c54&toName=%D0%9A%D0%B0%D1%81%D0%BC%D0%B5%D0%BD%D1%81%D0%BA-%D0%A3%D1%80%D0%B0%D0%BB%D1%8C%D1%81%D0%BA%D0%B8%D0%B9&when=13+%D0%B8%D1%8E%D0%BD%D1%8F
//https://rasp.yandex.ru/search/?fromName=Шадринск&toId=c54&toName=Касменск-Уральский&when=12+июня
//"https://rasp.yandex.ru/search/?fromName=" + stationDeparture + "&toId=c54&toName=" +
//            destination + "&when=" + day + "+июня"
}
