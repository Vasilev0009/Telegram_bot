package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    final String bus = "Автобусы";
    final String train = "Пригородные поезда";
    final String all = "Все";
    final String today = "Все";
    final String tomorrow = "Завтра";
    final String everyday = "Ежедневно";

    public Bot() {
        super("6269475408:AAHHxNe0IUwkqKXHAIBo4Gz1ZbWcbaeA950");
    }

    @Override
    public String getBotUsername() {
        return "BusSchedule66_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String response = null;
        Storage storage = new Storage();
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            //Извлекаем из объекта сообщение пользователя
            String inMess = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (inMess) {
                case "/start":
                    transports(chatId);
                    inMess = null;
                    break;
                case "/get":
                    inMess = storage.getRandQuote();
                    break;
                case "/test":
                    break;
                default:
                    inMess = "Сообщение не распознано";

            }
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(inMess);
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String text;
            EditMessageText message = new EditMessageText();
            message.setChatId(String.valueOf(chatId));

            switch (callbackData) {
                case train:
                    text = "train";
                    message.setText(text);
                    message.setMessageId((int) messageId);
                    break;
                case all:
                    text = "all";
                    message.setText(text);
                    message.setMessageId((int) messageId);
                    break;
                case bus:
                    text = "bus";
                    message.setText(text);
                    message.setMessageId((int) messageId);
                    break;
                default:
                    text = "Сообщение не распознано";
                    message.setText(text);
                    message.setMessageId((int) messageId);
            }
                    //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
/*            String response = parseMessage(inMess.getText());

            //Создаем объект будущей клавиатуры и выставляем нужные настройки
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
            replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования

            //Создаем список с рядами кнопок
            List<KeyboardRow> keyboardRows = new ArrayList<>();
            //Создаем один ряд кнопок и добавляем его в список
            KeyboardRow row = new KeyboardRow();
            row.add("Кусь");
            keyboardRows.add(row);
            //Добавляем одну кнопку с текстом "Просвяти" наш ряд
//            keyboardRow.add(new KeyboardButton("Просвяти"));
            //добавляем лист с одним рядом кнопок в главный объект
            replyKeyboardMarkup.setKeyboard(keyboardRows);

            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(response);
            message.setReplyMarkup(replyKeyboardMarkup);
*/
                    try {
                        execute(message); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
            }
        }
        private void day ( long chatId){
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText("Выбирите вид транспора");

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
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    private void transports ( long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выбирите дату отправления");

        InlineKeyboardMarkup InLineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

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
        rowInLine.add(busButton);
        rowInLine.add(allButton);

        rowsInLine.add(rowInLine);

        InLineKeyboard.setKeyboard(rowsInLine);
        message.setReplyMarkup(InLineKeyboard);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    }

