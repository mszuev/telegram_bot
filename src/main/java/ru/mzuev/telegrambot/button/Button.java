package ru.mzuev.telegrambot.button;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Button {
    void execute(Update update);
}
