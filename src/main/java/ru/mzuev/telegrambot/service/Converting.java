package ru.mzuev.telegrambot.service;

import ru.mzuev.telegrambot.parsing.MoneyParsing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class Converting {
    public static boolean isConverting = false;
    private static String value = "1";
    private static String decimalPattern = "([0-9]*)\\.([0-9]*)"; // check to Double
    private static String integerPattern = "[+-]?[0-9][0-9]*"; // check to Integer

    public static BigDecimal calculate (String currency, Boolean toRub) {

        MoneyParsing moneyParsing = new MoneyParsing(currency);
        BigDecimal valueToday = moneyParsing.getBigDecimalToday();
        BigDecimal result = BigDecimal.ZERO;

         if(toRub) {
             result = valueToday.multiply(BigDecimal.valueOf(Double.valueOf(value)));
         }

         if (!toRub){
             result = BigDecimal.valueOf(Double.valueOf(value)).divide(valueToday, 2, RoundingMode.CEILING);
         }

        result = result.setScale(2, RoundingMode.CEILING);
        return result;
    }

    public static boolean isNumeric(String message){
        if(Pattern.matches(decimalPattern, message) || Pattern.matches(integerPattern, message)){
            return true;
        } else return false;
    }

    public static boolean isPositive(String message){
        if(!message.startsWith("-")){
            return true;
        } else return false;
    }

    public static void setValue(String value) {
        Converting.value = value;
    }

    public static String getValue() {
        return value;
    }
}
