package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

public class StartCommand implements Button {

    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет! Я умею показывать актуальные курсы USD и EUR, конвертировать их в рубли и обратно." +
            "\nТакже я подскажу какой сегодня праздник :)";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = false;
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE, KeyboardType.WELCOME_KEYBOARD);
    }
}