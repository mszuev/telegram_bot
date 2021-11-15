package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

import java.math.BigDecimal;

public class ConvertingToRubButton implements Button{

    private final SendBotMessageService sendBotMessageService;
    private final String currency;

    public ConvertingToRubButton(SendBotMessageService sendBotMessageService, String currency) {
        this.sendBotMessageService = sendBotMessageService;
        this.currency = currency;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = true;
        BigDecimal result = Converting.calculate(currency, true);
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                "Введено значение " + Converting.getValue() + "\nРезультат " + result + " RUB", KeyboardType.CHOICE_CURRENCY_TO_KEYBOARD);
    }
}
