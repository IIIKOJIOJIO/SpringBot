package com.example.demo.repos;

import com.example.demo.models.TelegramUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<TelegramUserModel, Long> {
    TelegramUserModel findTelegramUserModelByTelegramId(String tgId);
}
