package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

public class ConvertingButton implements Button {

    private final SendBotMessageService sendBotMessageService;

    public ConvertingButton(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = true;
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                "Введите значение и выберите одну из кнопок меню", KeyboardType.CHOICE_CURRENCY_TO_KEYBOARD);
    }
}
