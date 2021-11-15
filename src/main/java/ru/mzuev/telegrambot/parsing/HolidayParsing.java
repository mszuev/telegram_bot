package ru.mzuev.telegrambot.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HolidayParsing {

    private List<String> holidayList = new ArrayList<>();
    private String holidays;

    private static final String URL = "https://kakoysegodnyaprazdnik.ru";

    public HolidayParsing() {

        try {
            Document document = Jsoup.connect(URL).get();
            Elements holiday = document.select("span[itemprop=text]");

            for (Element element : holiday) {
                holidayList.add(element.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        holidays = holidayList.stream().map(Object::toString).collect(Collectors.joining("\n\n"));
    }

    public String getHolidays() {
        return holidays;
    }
}
