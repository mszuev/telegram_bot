package ru.mzuev.telegrambot.service;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message, KeyboardType keyboard);
    void sendDocument(String chatId, String message);
}