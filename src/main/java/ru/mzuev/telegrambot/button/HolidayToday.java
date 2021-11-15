package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.parsing.HolidayParsing;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.KeyboardType;
import ru.mzuev.telegrambot.service.SendBotMessageService;

public class HolidayToday implements Button {
    private final SendBotMessageService sendBotMessageService;


    public HolidayToday(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        Converting.isConverting = false;
        HolidayParsing holidayParsing = new HolidayParsing();
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                "<b>Сегодня можно выпить за</b>\n\n" + holidayParsing.getHolidays(), KeyboardType.WELCOME_KEYBOARD);
    }
}
