package com.example.demo.helpers;

import com.example.demo.models.TelegramUserModel;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    private static UserHelper helper = null;

    public UserHelper() {
        helper = this;
    }

    @Autowired
    public UserRepo userRepo;

    public static void saveUserToDb(TelegramUserModel t) {
        helper.userRepo.save(t);
    }
    public static boolean findUserByThId(String id){
        TelegramUserModel telegramUserModel;
        telegramUserModel = helper.userRepo.findTelegramUserModelByTelegramId(id);
        if (telegramUserModel == null)
            return false;
        return true;
    }
}
