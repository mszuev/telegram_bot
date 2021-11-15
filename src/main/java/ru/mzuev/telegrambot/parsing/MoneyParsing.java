package ru.mzuev.telegrambot.parsing;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class MoneyParsing {

    private String valueToday;
    private String valueTomorrow;

    private BigDecimal bigDecimalToday;
    private BigDecimal bigDecimalTomorrow;

    private static String jsonStringURL;
    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";


    @Scheduled(fixedRate = 300000)
    private static void setJsonStringURL(){
        try {
            jsonStringURL = Jsoup.connect(URL).ignoreContentType(true).execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MoneyParsing() {
    }

    public MoneyParsing(String currency) {
            JSONObject json = new JSONObject(jsonStringURL);
            JSONObject json2 = json.getJSONObject("Valute");
            JSONObject json3 = json2.getJSONObject(currency);

            bigDecimalToday = (BigDecimal) json3.get("Previous");
            bigDecimalTomorrow = (BigDecimal) json3.get("Value");
            valueToday = bigDecimalToday.toString();
            valueTomorrow = bigDecimalTomorrow.toString();
    }

    public String getValueToday() {
        return valueToday;
    }

    public String getValueTomorrow() {
        return valueTomorrow;
    }

    public BigDecimal getBigDecimalToday() {
        return bigDecimalToday;
    }
}
