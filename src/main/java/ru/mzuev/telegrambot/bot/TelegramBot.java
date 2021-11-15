package ru.mzuev.telegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mzuev.telegrambot.button.ButtonContainer;
import ru.mzuev.telegrambot.button.adminButton.SendMessageButton;
import ru.mzuev.telegrambot.log.Log;
import ru.mzuev.telegrambot.service.Converting;
import ru.mzuev.telegrambot.service.SendBotMessageServiceImpl;
import static ru.mzuev.telegrambot.button.ButtonName.*;

import java.util.Arrays;
import java.util.List;

@Component
public class TelegramBot extends TelegramWebhookBot {

    private String user_first_name;
    private String user_last_name;
    private String user_username;
    private String bot_answer;
    private long user_id;

    @Value("${bot.webHookPath}")
    private String webHookPath;

    @Value("${bot.username}")
    private String botUserName;

    @Value("${bot.token}")
    private String botToken;

    private static String adminUserName;

    private final ButtonContainer buttonContainer;
    private final Log log;

    public TelegramBot() {

        this.buttonContainer = new ButtonContainer(new SendBotMessageServiceImpl(this));
        this.log = new Log();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            user_first_name = update.getMessage().getChat().getFirstName();
            user_last_name = update.getMessage().getChat().getLastName();
            user_username = update.getMessage().getChat().getUserName();
            user_id = update.getMessage().getChat().getId();
            String messageWithoutTrim = update.getMessage().getText();

            if(messageWithoutTrim.startsWith(SEND_COMMAND.getButtonName())){
                List<String> messageList = Arrays.asList(messageWithoutTrim.split(" ", 3));
                SendMessageButton.setId(messageList.get(1));
                SendMessageButton.setMessage(messageList.get(2));

                buttonContainer.retrieveButton(messageList.get(0)).execute(update);

            }else {

                String message = messageWithoutTrim.trim();

                if (Converting.isConverting) {
                    if (Converting.isNumeric(message) && Converting.isPositive(message)) {
                        Converting.setValue(message);
                    }
                }

                buttonContainer.retrieveButton(message).execute(update);

                log.setUser_id(user_id);
                log.setFirst_name(user_first_name);
                log.setLast_name(user_last_name);
                log.setUser_username(user_username);
                log.setTxt(message);
                log.setAnswer(bot_answer);

                if (!user_username.equals(adminUserName)) {
                    log.addLog();
                }
            }
        }
        return null;
    }

    @Value("${bot.admin}")
    public void setAdminUserName(String admin) {
        adminUserName = admin;
    }

    public static String getAdminUserName() {
        return adminUserName;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void setBot_answer(String bot_answer) {
        this.bot_answer = bot_answer;
    }
}
