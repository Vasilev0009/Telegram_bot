package org.example;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("test");
        try {
            TelegramBotsApi BotsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotsApi.registerBot(new Bot("6269475408:AAHHxNe0IUwkqKXHAIBo4Gz1ZbWcbaeA95"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}

