package ru.mzuev.telegrambot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    public static ReplyKeyboardMarkup keyboardMarkup(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup); // connecting message to keyboard
        replyKeyboardMarkup.setSelective(true); // selecting particular users
        replyKeyboardMarkup.setResizeKeyboard(true); // size optimize
        replyKeyboardMarkup.setOneTimeKeyboard(false); // hide after push

        return replyKeyboardMarkup;
    }


    public static void welcomeKeyboard(SendMessage sendMessage){ // Create Keyboards

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow(); // row
        firstRow.add(new KeyboardButton("Курсы валют")); // add button in row
        firstRow.add(new KeyboardButton("Какой сегодня праздник?"));
        keyboardRows.add(firstRow);
        keyboardMarkup(sendMessage).setKeyboard(keyboardRows);
    }

    public static void ChoiceCurrencyKeyboard(SendMessage sendMessage){

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("USD"));
        firstRow.add(new KeyboardButton("EUR"));
        keyboardRows.add(firstRow);

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Конвертировать"));
        keyboardRows.add(secondRow);

        keyboardMarkup(sendMessage).setKeyboard(keyboardRows);
    }

    public static void conversionOrBackKeyboard(SendMessage sendMessage){

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Конвертировать"));

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("Главное меню"));

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);

        keyboardMarkup(sendMessage).setKeyboard(keyboardRows);
    }

    public static void conversionUsdKeyboard(SendMessage sendMessage){

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton( "USD -> RUB"));
        firstRow.add(new KeyboardButton("RUB -> USD"));

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton( "EUR -> RUB"));
        secondRow.add(new KeyboardButton("RUB -> EUR"));

        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("Главное меню"));

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);
        keyboardRows.add(thirdRow);

        keyboardMarkup(sendMessage).setKeyboard(keyboardRows);
    }
}