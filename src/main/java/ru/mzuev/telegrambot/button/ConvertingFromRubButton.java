package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

import java.math.BigDecimal;

public class ConvertingFromRubButton implements Button {

    private final SendBotMessageService sendBotMessageService;
    private final String currency;

    public ConvertingFromRubButton(SendBotMessageService sendBotMessageService, String currency) {
        this.sendBotMessageService = sendBotMessageService;
        this.currency = currency;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = true;
        BigDecimal result = Converting.calculate(currency, false);
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                "Введено значение " + Converting.getValue() + "\nРезультат " + result + " " + currency, KeyboardType.CHOICE_CURRENCY_TO_KEYBOARD);
    }

}
