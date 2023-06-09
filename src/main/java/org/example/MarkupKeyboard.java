package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MarkupKeyboard {
    private final Bot bot;
    InlineKeyboardMarkup inLineKeyboard = new InlineKeyboardMarkup();
    private List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
    private int key = 0;

    public MarkupKeyboard(Bot bot) {
        this.bot = bot;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void getKeyboard() {

        if (key == 1) {
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();
            List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
            List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();

            var trainButton = new InlineKeyboardButton();
            trainButton.setText("Пригородные поезда");
            trainButton.setCallbackData(bot.train);

            var busButton = new InlineKeyboardButton();
            busButton.setText("Автобусы");
            busButton.setCallbackData(bot.bus);

            var allButton = new InlineKeyboardButton();
            allButton.setText("Все");
            allButton.setCallbackData(bot.all);

            rowInLine.add(trainButton);
            rowInLine2.add(busButton);
            rowInLine3.add(allButton);

            rowsInLine.add(rowInLine);
            rowsInLine.add(rowInLine2);
            rowsInLine.add(rowInLine3);

            inLineKeyboard.setKeyboard(rowsInLine);
        } else if (key == 2) {
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();
            List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
            List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();

            var trainButton = new InlineKeyboardButton();
            trainButton.setText("Сегодня");
            trainButton.setCallbackData(bot.today);

            var busButton = new InlineKeyboardButton();
            busButton.setText("Завтра");
            busButton.setCallbackData(bot.tomorrow);

            var allButton = new InlineKeyboardButton();
            allButton.setText("Ежедневно");
            allButton.setCallbackData(bot.everyday);

            rowInLine.add(trainButton);
            rowInLine2.add(busButton);
            rowInLine3.add(allButton);

            rowsInLine.add(rowInLine);
            rowsInLine.add(rowInLine2);
            rowsInLine.add(rowInLine3);

            inLineKeyboard.setKeyboard(rowsInLine);
        }
    }
}
