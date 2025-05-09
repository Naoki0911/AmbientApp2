package com.example.AmbientApp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AmbientApp2Controller {

    // ファイル名・表示ラベル・カテゴリーを持つレコード定義
    public record Sound(String fileName, String label, String category) { }

    private final List<Sound> soundList = List.of(
      new Sound("ウミネコ.wav", "ウミネコ", "生物"),
      new Sound("カラス.wav", "カラス", "生物"),
      new Sound("スズメ.wav", "スズメ", "生物"),
      new Sound("ツクツクボウシ.wav", "ツクツクボウシ", "生物"),
      new Sound("ヒグラシ.wav", "ヒグラシ", "生物"),
      new Sound("ミンミンゼミ.wav", "ミンミンゼミ", "生物"),
      new Sound("オオルリ.wav", "オオルリ", "生物"),
      new Sound("雨.wav", "雨", "自然"),
      new Sound("雷雨.wav", "雷雨", "自然"),
      new Sound("川.wav", "川", "自然"),
      new Sound("洞窟.wav", "洞窟", "自然"),
      new Sound("草木.wav", "草木", "自然"),
      new Sound("駅の改札.wav", "駅の改札", "場所"),
      new Sound("下水道.wav", "下水道", "場所"),
      new Sound("街の道路.wav", "街の道路", "場所"),
      new Sound("学校の廊下.wav", "学校の廊下", "場所"),
      new Sound("交差点.wav", "交差点", "場所"),
      new Sound("時計.wav", "時計", "生活"),
      new Sound("時計（古い）.wav",   "時計（古い）", "生活"),
      new Sound("鉛筆.wav",   "鉛筆", "生活"),
      new Sound("食卓.wav", "食卓", "生活"),
      new Sound("洗濯機.wav", "洗濯機", "生活"),
      new Sound("焚き火.wav", "焚き火", "生活"),
      new Sound("電車.wav", "電車", "場所"),
      new Sound("波.wav", "波", "自然"),
      new Sound("風鈴.wav", "風鈴", "生活"),
      new Sound("風.wav", "風", "自然"),
      new Sound("夜の繁華街.wav", "夜の繁華街", "場所")
    );

 // このメソッドの目的は音源データをカテゴリーごとに分けてindex.htmlに渡すこと
    @GetMapping("/") 
    public String index(Model model) {  // Model modelはSpring MVCのVに値を渡すための入れ物
        // カテゴリーごとにグループ化
        Map<String, List<Sound>> soundsByCategory =
            soundList.stream() // stream()は遅延評価と言って、collect(Collectors.〜)で呼び出されるまで実際にコレクションは生成されない
                     .collect(Collectors.groupingBy(Sound::category)); // キーがカテゴリーで、値が同じカテゴリーのサウンドのリストとなる

        model.addAttribute("soundsByCategory", soundsByCategory); // soundsByCategory という名前で、Map<String, List<Sound>> のデータをビュー（Thymeleafテンプレート）に渡す
        return "index"; // templates/index.htmlを返す
    }
}