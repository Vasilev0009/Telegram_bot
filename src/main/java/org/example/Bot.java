package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    int stage = 0;
    String tapesTrans = null;
    String destinationPoint = null;
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
            inMess = scriptMenu.getInMess();

            switch (stage = scriptMenu.getStage()) {
                case 1 -> {
                    sendMessage(chatId, "Выберите вид транспорта", stage);
                }
                case 3 -> {
                    destinationPoint = inMess + "/66/";
                    destination(chatId);

                }
                case 4 -> {
                    stage = 0;
                    url = "http://расписание.рф/" + tapesTrans + destinationPoint + inMess + data;
                    parser.setUrl(url);
                    inTrains = parser.getParserList();
                    for (String inTrain : inTrains) {
                        sendMessage(chatId, inTrain,stage = 0);
                    }
                }
                default -> sendMessage(chatId, inMess, stage);
            }

        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
//            String text;
//            EditMessageText message = new EditMessageText();
//            message.setChatId(String.valueOf(chatId));

            switch (callbackData) {
                case train -> {
                    tapesTrans = "электричка/66/";
                    sendMessage(chatId,"Выбирите дату отправления",stage =2);
                }
                case all -> {
                    tapesTrans = "все/66/";
                    sendMessage(chatId,"Выбирите дату отправления",stage =2);
                }
                case bus -> {
                    tapesTrans = "автобус/66/";
                    sendMessage(chatId,"Выбирите дату отправления",stage =2);
                }
                case today -> {
                    data = "/сегодня";
                    departure(chatId);
                }
                case tomorrow -> {
                    data = "/завтра";
                    departure(chatId);
                }
                case everyday -> {
                    data = null;
                    departure(chatId);
                }
                default -> {
//                    text = "Сообщение не распознано";
//                    message.setText(text);
//                    message.setMessageId((int) messageId);
                }
            }
        }
    }

    private void destination(long chatId) {
        sendMessage(chatId, "Выберите станцию назначения", stage = 4);
    }
    private void departure(long chatId) {
        sendMessage(chatId, "Выберите станцию отправления", stage = 3);
    }
    private void sendMessage (long chatId, String textToSend, int stage){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        if( 0 < stage && stage < 3) {
            MarkupKeyboard markupkeyboard = new MarkupKeyboard(this);
            markupkeyboard.setKey(stage);
            markupkeyboard.getKeyboard();
            message.setReplyMarkup(markupkeyboard.inLineKeyboard);
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}


