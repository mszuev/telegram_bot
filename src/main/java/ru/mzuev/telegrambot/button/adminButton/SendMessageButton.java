package ru.mzuev.telegrambot.button.adminButton;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.bot.TelegramBot;
import ru.mzuev.telegrambot.button.Button;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

import static ru.mzuev.telegrambot.button.UnknownButton.UNKNOWN_MESSAGE;

public class SendMessageButton implements Button {
    private final SendBotMessageService sendBotMessageService;
    private static String id;
    private static String message;

    public SendMessageButton(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public static void setId(String id) {
        SendMessageButton.id = id;
    }

    public static void setMessage(String message) {
        SendMessageButton.message = message;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = false;

        if(update.getMessage().getChat().getUserName().equals(TelegramBot.getAdminUserName())) {
            sendBotMessageService.sendMessage(id, message, KeyboardType.WELCOME_KEYBOARD);
        } else sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                UNKNOWN_MESSAGE, KeyboardType.WELCOME_KEYBOARD);
    }
}
