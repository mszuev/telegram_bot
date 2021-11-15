package ru.mzuev.telegrambot.button;

import com.google.common.collect.ImmutableMap;
import ru.mzuev.telegrambot.button.adminButton.LogButton;
import ru.mzuev.telegrambot.button.adminButton.SendMessageButton;
import ru.mzuev.telegrambot.service.SendBotMessageService;
import static ru.mzuev.telegrambot.button.ButtonName.*;


public class ButtonContainer {

    private final ImmutableMap <String, Button> buttonMap;
    private final Button unknownButton;

    public ButtonContainer(SendBotMessageService sendBotMessageService) {

        buttonMap = ImmutableMap.<String, Button> builder()
                .put(START_COMMAND.getButtonName(), new StartCommand(sendBotMessageService))
                .put(LOG_COMMAND.getButtonName(), new LogButton(sendBotMessageService))
                .put(SEND_COMMAND.getButtonName(), new SendMessageButton(sendBotMessageService))
                .put(MAIN_MENU_BUTTON.getButtonName(), new MainMenuButton(sendBotMessageService))
                .put(COURSE_BUTTON.getButtonName(), new CourseButton(sendBotMessageService))
                .put(HOLIDAY_TODAY.getButtonName(), new HolidayToday(sendBotMessageService))
                .put(USD_BUTTON.getButtonName(), new USDButton(sendBotMessageService, USD_BUTTON.getButtonName()))
                .put(EUR_BUTTON.getButtonName(), new EURButton(sendBotMessageService, EUR_BUTTON.getButtonName()))
                .put(USD_TO_RUB_BUTTON.getButtonName(), new ConvertingToRubButton(sendBotMessageService, USD_BUTTON.getButtonName()))
                .put(RUB_TO_USD_BUTTON.getButtonName(), new ConvertingFromRubButton(sendBotMessageService, USD_BUTTON.getButtonName()))
                .put(EUR_TO_RUB_BUTTON.getButtonName(), new ConvertingToRubButton(sendBotMessageService, EUR_BUTTON.getButtonName()))
                .put(RUB_TO_EUR_BUTTON.getButtonName(), new ConvertingFromRubButton(sendBotMessageService, EUR_BUTTON.getButtonName()))
                .put(CONVERTING_BUTTON.getButtonName(), new ConvertingButton(sendBotMessageService))
                .build();

        unknownButton = new UnknownButton(sendBotMessageService);
    }

    public Button retrieveButton(String buttonIdentifier) {
        return buttonMap.getOrDefault(buttonIdentifier, unknownButton);
    }
}
