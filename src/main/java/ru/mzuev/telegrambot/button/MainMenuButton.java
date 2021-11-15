package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

public class MainMenuButton implements Button{

    private final SendBotMessageService sendBotMessageService;

    public MainMenuButton(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = false;
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Главное меню", KeyboardType.WELCOME_KEYBOARD);
    }
}
