package com.example.AmbientApp2;

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

    // サンプル音源リスト（実際はもっと増やしてください）
    private final List<Sound> soundList = List.of(
      new Sound("ウミネコ.wav", "ウミネコ", "生物"),
      new Sound("カラス.wav", "カラス", "生物"),
      new Sound("スズメ.wav", "スズメ", "生物"),
      new Sound("ツクツクボウシ.wav", "ツクツクボウシ", "生物"),
      new Sound("ヒグラシ.wav", "ヒグラシ", "生物"),
      new Sound("ミンミンゼミ.wav", "ミンミンゼミ", "生物"),
      new Sound("雨.wav", "雨", "自然"),
      new Sound("駅の改札.wav", "駅の改札", "場所"),
      new Sound("下水道.wav", "下水道", "場所"),
      new Sound("街の道路.wav", "街の道路", "場所"),
      new Sound("学校の廊下", "学校の廊下", "場所"),
      new Sound("時計.wav", "時計", "生活"),
      new Sound("時計（古い）.wav",   "時計（古い）", "生活"),
      new Sound("食卓.wav", "食卓", "生活"),
      new Sound("水中.wav", "水中", "場所"),
      new Sound("電車.wav", "電車", "場所"),
      new Sound("波.wav", "波", "自然"),
      new Sound("風鈴.wav", "風鈴", "生活"),
      new Sound("風.wav", "風", "自然"),
      new Sound("夜の繁華街.wav", "夜の繁華街", "場所")
    );

    @GetMapping("/")
    public String index(Model model) {
        // カテゴリーごとにグループ化
        Map<String, List<Sound>> soundsByCategory =
            soundList.stream()
                     .collect(Collectors.groupingBy(Sound::category));

        model.addAttribute("soundsByCategory", soundsByCategory);
        return "index";
    }
}