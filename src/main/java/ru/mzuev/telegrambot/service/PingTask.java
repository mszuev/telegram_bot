package ru.mzuev.telegrambot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PingTask {

    @Scheduled(fixedRate = 1200000)
    public void ping() {
        try {
            URL url = new URL("https://www.google.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
