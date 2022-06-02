package com.example.demo.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tg_user")
public class TelegramUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    private String name;

    @Column(name = "telegram_username")
    private String username;

    @Column(name = "telegram_id")
    private String telegramId;
}
