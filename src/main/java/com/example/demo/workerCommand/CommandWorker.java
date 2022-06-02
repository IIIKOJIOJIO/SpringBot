package com.example.demo.workerCommand;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandWorker {
    SendMessage start(Update update);
    SendMessage sendDefaultMessage(Update update);
}
