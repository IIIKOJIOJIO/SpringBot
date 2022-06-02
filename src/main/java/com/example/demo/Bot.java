package com.example.demo;

import com.example.demo.helpers.UserHelper;
import com.example.demo.models.TelegramUserModel;
import com.example.demo.workerCommand.BookCommand;
import com.example.demo.workerCommand.CommandWorker;
import com.example.demo.workerCommand.LoginCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    boolean ifNameReqared = false;

    @Override
    public String getBotUsername() {
        return "Spring_first_bot_overone_bot";
    }

    @Override
    public String getBotToken() {
        return "5318913003:AAHUs6XuBf6u6MK-MzpJi_WY3ebQLK1XNng";
    }

    @Override
    public void onUpdateReceived(Update update) {

        KeyboardRow k1 = new KeyboardRow();
        if (!UserHelper.findUserByThId(String.valueOf(update.getMessage().getFrom().getId()))){
            k1.add(new KeyboardButton("LogIn"));
        }
        k1.add(new KeyboardButton("Записаться"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(k1));
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setText("Добро пожаловать");

        List<CommandWorker> list = new ArrayList<>();
        list.add(new LoginCommand());
        list.add(new BookCommand());

        for (CommandWorker c : list) {
            if (c.start(update) != null) {
                sendMessage = c.start(update);
                break;
            }
        }

        if (update.getMessage().getText().equals("Введите свое имя")) {
            ifNameReqared = true;
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
