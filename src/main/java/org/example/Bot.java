package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    int stage = 0;
    String tapesTrans = null;
    String data = null;
    final String bus = "Автобусы";
    final String train = "Пригородные поезда";
    final String all = "Все";
    final String today = "Сегодня";
    final String tomorrow = "Завтра";
    final String everyday = "Ежедневно";

    String url = "http://расписание.рф/автобус/66/Екатеринбург/45/Шадринск/завтра";

    public Bot() {
        super("6269475408:AAHHxNe0IUwkqKXHAIBo4Gz1ZbWcbaeA950");
    }

    @Override
    public String getBotUsername() {
        return "BusSchedule66_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Parser parser = new Parser();
        ScriptMenu scriptMenu = new ScriptMenu();
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            //Извлекаем из объекта сообщение пользователя
            String inMess = update.getMessage().getText();
            ArrayList <String> inTrains;
            long chatId = update.getMessage().getChatId();
            scriptMenu.setInMess(inMess, stage);
            scriptMenu.getInMess();

            switch (scriptMenu.getStage()) {
                case 1 -> transports(chatId);
                case 2 -> {
                    stage = 0;
                    url = "http://расписание.рф/" + tapesTrans + inMess + data;
                    parser.setUrl(url);
                    inTrains = parser.getParserList();
                    SendMessage message = new SendMessage();
                    message.setChatId(update.getMessage().getChatId().toString());
                    for (String inTrain : inTrains) {

                        message.setText(inTrain);
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
                default -> inMess = "Неверная команда. Попробуйте /start";
            }
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(inMess);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String text;
            EditMessageText message = new EditMessageText();
            message.setChatId(String.valueOf(chatId));

            switch (callbackData) {
                case train -> {
                    tapesTrans = "электричка/66/Екатеринбург/66/";
                    day(chatId);
                }
                case all -> {
                    tapesTrans = "все/66/Екатеринбург/66/";
                    day(chatId);
                }
                case bus -> {
                    tapesTrans = "автобус/66/Екатеринбург/66/";
                    day(chatId);
                }
                case today -> {
                    data = "/сегодня";
                    destination(chatId);
                }
                case tomorrow -> {
                    data = "/завтра";
                    destination(chatId);
                }
                case everyday -> {
                    data = null;
                    destination(chatId);
                }
                default -> {
                    text = "Сообщение не распознано";
                    message.setText(text);
                    message.setMessageId((int) messageId);
                }
            }
        }
    }

    private void day(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выбирите дату отправления");

        InlineKeyboardMarkup InLineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var trainButton = new InlineKeyboardButton();
        trainButton.setText("Сегодня");
        trainButton.setCallbackData(today);

        var busButton = new InlineKeyboardButton();
        busButton.setText("Завтра");
        busButton.setCallbackData(tomorrow);

        var allButton = new InlineKeyboardButton();
        allButton.setText("Ежедневно");
        allButton.setCallbackData(everyday);

        rowInLine.add(trainButton);
        rowInLine.add(busButton);
        rowInLine.add(allButton);

        rowsInLine.add(rowInLine);

        InLineKeyboard.setKeyboard(rowsInLine);
        message.setReplyMarkup(InLineKeyboard);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void transports(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выбирите вид транспорта");

        InlineKeyboardMarkup InLineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();

        var trainButton = new InlineKeyboardButton();
        trainButton.setText("Пригородные поезда");
        trainButton.setCallbackData(train);

        var busButton = new InlineKeyboardButton();
        busButton.setText("Автобусы");
        busButton.setCallbackData(bus);

        var allButton = new InlineKeyboardButton();
        allButton.setText("Все");
        allButton.setCallbackData(all);

        rowInLine.add(trainButton);
        rowInLine2.add(busButton);
        rowInLine3.add(allButton);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);

        InLineKeyboard.setKeyboard(rowsInLine);
        message.setReplyMarkup(InLineKeyboard);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void destination(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выбирите станцию назначения");
        stage =2;
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}


