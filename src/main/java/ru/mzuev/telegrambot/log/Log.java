package ru.mzuev.telegrambot.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Log {

    private String log;
    private static List<String> logList = new ArrayList<>();

    private long user_id;
    private String first_name;
    private String last_name;
    private String user_username;
    private String txt;
    private String bot_answer;


    public void addLog() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        log = dateFormat.format(new Date()) +
                "\n" +first_name + " " + last_name + " (" + user_username + ") " + user_id + ":\n" + txt +
                "\nBot answer:\n" + bot_answer;

        logList.add(log);
    }

    public static String getLogList() {
        return logList.stream().map(Object::toString).collect(Collectors.joining("\n\n\n"));
    }

    public static File getLogTxt() {
        String toWrite = getLogList();
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("logs", ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(tmpFile)) {
            writer.write(toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmpFile;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setAnswer(String bot_answer) {
        this.bot_answer = bot_answer;
    }
}
