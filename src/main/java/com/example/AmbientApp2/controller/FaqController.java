package com.example.AmbientApp2.controller;

import com.example.AmbientApp2.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FaqController {

    @GetMapping("/faq")
    public String showFaqPage() {
        return "faq";
    }
    
    private final MailService mailService;
    
    public FaqController(MailService mailService) {
    	this.mailService = mailService; // MailServiceクラスをimportするだけではsendQuestion()を使えないので、依存性注入することでMailServiceクラスのインスタンス化を果たしている
    }
    
    @PostMapping("/faq")
    public String submitQuestion(@RequestParam String email, @RequestParam String question) {
        String to = "am61entmaker@gmail.com";  // faq.htmlで受け取った情報をtoとbodyに格納
        String subject = "FAQからの質問";
        String body = "送信者: " + email + "\n\n質問内容:\n" + question;
        mailService.sendQuestion(to, subject, body); // 格納された情報をsendQuestionメソッドでメール送信を依頼する
        return "redirect:/faq?sent=true";
    }
}


