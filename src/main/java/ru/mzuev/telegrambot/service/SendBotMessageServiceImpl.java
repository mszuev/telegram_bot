package ru.mzuev.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mzuev.telegrambot.bot.TelegramBot;
import ru.mzuev.telegrambot.log.Log;


@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final TelegramBot bot;

    @Autowired
    public SendBotMessageServiceImpl(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message, KeyboardType keyboardType) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        bot.setBot_answer(message);

        try {

            switch (keyboardType) {

                case WELCOME_KEYBOARD:
                    Keyboard.welcomeKeyboard(sendMessage);
                    break;

                case CHOICE_CURRENCY_KEYBOARD:
                    Keyboard.ChoiceCurrencyKeyboard(sendMessage);
                    break;

                case CONVERSION_OR_BACK_KEYBOARD:
                    Keyboard.conversionOrBackKeyboard(sendMessage);
                    break;

                case CHOICE_CURRENCY_TO_KEYBOARD:
                    Keyboard.conversionUsdKeyboard(sendMessage);
            }

            bot.execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendDocument(String chatId, String message) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile(Log.getLogTxt(), "logs.txt"));


        try {
            if (!Log.getLogList().isEmpty()) {
                bot.execute(sendDocument);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
