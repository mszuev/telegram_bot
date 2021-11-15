package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.parsing.MoneyParsing;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

public class USDButton implements Button {

    private final SendBotMessageService sendBotMessageService;
    private final String currency;

    public USDButton(SendBotMessageService sendBotMessageService, String currency) {
        this.sendBotMessageService = sendBotMessageService;
        this.currency = currency;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = false;
        MoneyParsing moneyParsing = new MoneyParsing(currency);
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                "Курс " + currency + " сегодня: " + moneyParsing.getValueToday() +
                        "\nКурс " + currency + " завтра:   " + moneyParsing.getValueTomorrow(), KeyboardType.CONVERSION_OR_BACK_KEYBOARD);
    }
}
