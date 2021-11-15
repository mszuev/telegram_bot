package ru.mzuev.telegrambot.button.adminButton;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.bot.TelegramBot;
import ru.mzuev.telegrambot.button.Button;
import ru.mzuev.telegrambot.log.Log;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

import static ru.mzuev.telegrambot.button.UnknownButton.UNKNOWN_MESSAGE;

public class LogButton implements Button {
    private final SendBotMessageService sendBotMessageService;

    public LogButton(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = false;

        if(update.getMessage().getChat().getUserName().equals(TelegramBot.getAdminUserName())) {
            sendBotMessageService.sendDocument(update.getMessage().getChatId().toString(), Log.getLogList());
        } else sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                    UNKNOWN_MESSAGE, KeyboardType.WELCOME_KEYBOARD);
    }
}
