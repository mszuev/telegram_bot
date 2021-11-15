package ru.mzuev.telegrambot.button;

public enum ButtonName {

    START_COMMAND("/start"),
    LOG_COMMAND("/log"),
    SEND_COMMAND("/send"),
    COURSE_BUTTON("Курсы валют"),
    HOLIDAY_TODAY("Какой сегодня праздник?"),
    MAIN_MENU_BUTTON("Главное меню"),
    CONVERTING_BUTTON("Конвертировать"),
    USD_BUTTON("USD"),
    EUR_BUTTON("EUR"),
    USD_TO_RUB_BUTTON("USD -> RUB"),
    RUB_TO_USD_BUTTON("RUB -> USD"),
    EUR_TO_RUB_BUTTON("EUR -> RUB"),
    RUB_TO_EUR_BUTTON("RUB -> EUR");

    private final String buttonName;

    ButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
