package com.example.AmbientApp2.service;

import org.springframework.mail.SimpleMailMessage; // Spring Frameworkが提供している既存のクラス
import org.springframework.mail.javamail.JavaMailSender; // Spring Frameworkが提供している既存のインタフェース
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
    private final JavaMailSender mailSender; // 実際に送るためのsendを使用するために変数を用意
    
    public MailService(JavaMailSender mailSender) { // 「JavaMailSender型のオブジェクトを MailServiceクラスに注入してね」という指示
    	this.mailSender = mailSender; // Spring Bootが内部でJavaMailSenderImpl（sendメソッドを実現している具象クラス）を作りDI(依存性注入）機構がインスタンス化する
    }
    
    public void sendQuestion(String to, String subject, String text) {
    	SimpleMailMessage message = new SimpleMailMessage(); // org.springframework.mail.SimpleMailMessage パッケージにある Spring公式のメール送信用クラス
    	message.setTo(to); // テキストベースのシンプルなメールを作るためのクラスであるSimpleMailMessageクラスが提供するメソッド
    	message.setSubject(subject);
    	message.setText(text);
    	mailSender.send(message); // FaqControllerクラスのsubmitQuestionメソッドで格納された各情報をmessageにセット
    } // 事前にapplication.propertiesに設定した情報を元にSMTP(メール送信用のプロトコル)を使ってメールサーバーに接続
    
}
