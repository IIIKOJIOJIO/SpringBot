package com.example.demo.workerCommand;

import com.example.demo.helpers.UserHelper;
import com.example.demo.models.TelegramUserModel;
import org.checkerframework.checker.units.qual.K;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class LoginCommand implements CommandWorker{
    @Override
    public SendMessage start(Update update) {
        if (update.hasMessage()&&((!update.getMessage().getText().equals("LogIn")
        &&!update.getMessage().getText().equals("Хочу остаться анонимом")
        &&!update.getMessage().getText().equals("Ввести свое имя")))) {
            return null;
        }
        else if (update.getMessage().getText().equals("LogIn")){
            SendMessage sendMessage = new SendMessage();
            return sendMessage = sendDefaultMessage(update);
        }
        else if (update.getMessage().getText().equals("Хочу остаться анонимом")){
            TelegramUserModel telegramUserModel = new TelegramUserModel();
            User user = new User();
            user = update.getMessage().getFrom();
            telegramUserModel.setUsername(user.getUserName());
            telegramUserModel.setTelegramId(String.valueOf(user.getId()));
            UserHelper.saveUserToDb(telegramUserModel);
        }else if (update.getMessage().getText().equals("Ввести свое имя")){
            TelegramUserModel telegramUserModel = new TelegramUserModel();
            User user;
            user = update.getMessage().getFrom();
            telegramUserModel.setName(user.getFirstName()+" "+user.getLastName());
            telegramUserModel.setUsername(user.getUserName());
            telegramUserModel.setTelegramId(String.valueOf(user.getId()));
            UserHelper.saveUserToDb(telegramUserModel);
        }
        return null;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Введите свое имя");

        KeyboardRow keyboard = new KeyboardRow();
        keyboard.add(new KeyboardButton("Хочу остаться анонимом"));
        keyboard.add(new KeyboardButton("Ввести свое имя"));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboard));

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
