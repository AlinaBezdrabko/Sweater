package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting"; // возвращаем имя файла, который хотим отобразить
    }

    @GetMapping("/main") //не указываем ничего в скобках, значит,
                //при входе на localhost мы будем получать то, что содержится здесь
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);    // в model мы ложим все то, что хотим вернуть пользователю,
                                            // здесь мы в поле "name" ложим то, что получили в параметр name
        return "main";
    }
    // @RequestParam "выдергивает" из запросов (из формы (при post запросах) или из url (при get запросах)) поля по имени.
    // Например, в "@RequestParam String text" вытягиевает text
    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }

}