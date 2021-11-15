package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

public class CourseButton implements Button {

    private final SendBotMessageService sendBotMessageService;

    public CourseButton(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = false;
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                "Выберите валюту", KeyboardType.CHOICE_CURRENCY_KEYBOARD);
    }
}
