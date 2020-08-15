package com.message.controller;

import com.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String message() {
        Boolean result = this.messageService.message();
        if (result) {
            return "短信验证码及邮件发送成功";
        }
        return "发送失败";
    }


}
