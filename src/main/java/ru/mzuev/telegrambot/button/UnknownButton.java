package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

public class UnknownButton implements Button {

    public static final String UNKNOWN_MESSAGE = "Неизвестная команда";

    private final SendBotMessageService sendBotMessageService;

    public UnknownButton(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        if (!Converting.isConverting) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                    UNKNOWN_MESSAGE, KeyboardType.WELCOME_KEYBOARD);
        }
    }
}

