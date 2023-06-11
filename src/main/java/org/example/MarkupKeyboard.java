package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MarkupKeyboard {
    InlineKeyboardMarkup inLineKeyboard = new InlineKeyboardMarkup();
    private final List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
    public void getKeyboard(int stage) {
        String firstLine = "null";
        String secondLine = "null";
        String thirdLine = "null";
        String firstLineText = "null";
        String secondLineText = "null";
        String thirdLineText = "null";

        if (stage == 1) {
            firstLine = "train";
            secondLine = "bus";
            thirdLine = "all";
            firstLineText = "Пригородные поезда";
            secondLineText = "Автобусы";
            thirdLineText = "Все";
        } else if (stage == 2) {

            firstLine = "today";
            secondLine = "tomorrow";
            thirdLine = "everyday";
            firstLineText = "Сегодня";
            secondLineText = "Завтра";
            thirdLineText = "Ежедневно";
        }
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();

        var trainButton = new InlineKeyboardButton();
        trainButton.setText(firstLineText);
        trainButton.setCallbackData(firstLine);

        var busButton = new InlineKeyboardButton();
        busButton.setText(secondLineText);
        busButton.setCallbackData(secondLine);

        var allButton = new InlineKeyboardButton();
        allButton.setText(thirdLineText);
        allButton.setCallbackData(thirdLine);

        rowInLine.add(trainButton);
        rowInLine2.add(busButton);
        rowInLine3.add(allButton);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);

        inLineKeyboard.setKeyboard(rowsInLine);
    }
}
