package oit.is.z1439.kaizi.janken.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1439.kaizi.janken.model.Janken;
import oit.is.z1439.kaizi.janken.model.Entry;
import oit.is.z1439.kaizi.janken.model.UserMapper;
import oit.is.z1439.kaizi.janken.model.Match;
import oit.is.z1439.kaizi.janken.model.MatchMapper;
import oit.is.z1439.kaizi.janken.model.MatchInfo;
import oit.is.z1439.kaizi.janken.model.MatchInfoMapper;

@Controller
public class Lec02Controller {
  private int user_id;
  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @Autowired
  private MatchInfoMapper matchInfoMapper;

  @GetMapping("/lec02")
  @Transactional
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    // データベースエラー
    user_id = userMapper.selectIdByName(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("matches", matchMapper.selectAllMatches());
    model.addAttribute("users", userMapper.selectAllUsers());
    return "lec02.html";
  }

  // matchのresultでlec02/geamリンクをGetMapping
  @GetMapping("/lec02/geam")
  @Transactional

  public String lec02_geam(@RequestParam Integer cpu_id, @RequestParam Integer jankenhand, Principal prin,
      ModelMap model) {
    // jankenインスタンス
    Janken hand = new Janken(jankenhand);
    // Matchインスタンス
    Match match = new Match();
    String UserHand = hand.playerhand();
    String CpuHand = hand.cpuhand();

    // matchのsetUser保存
    match.setUser_1(user_id);
    match.setUser_2(cpu_id);
    match.setUser_1_hand(UserHand);
    match.setUser_2_hand(CpuHand);

    // データベースに保存する
    matchMapper.insertMatch(match);
    model.addAttribute("cpu_id", cpu_id);
    model.addAttribute("user_name", prin.getName());
    model.addAttribute("cpu_name", userMapper.selectNameById(cpu_id));
    // 自分の手
    model.addAttribute("my_hand", UserHand);
    // 相手の手
    model.addAttribute("your_hand", CpuHand);
    // 試合結果
    model.addAttribute("Result", hand.score());
    return "match.html";
  }

  // matchのGetMapping
  @GetMapping("/match")
  @Transactional
  public String match(@RequestParam Integer id, Principal prin, ModelMap model) {
    MatchInfo matchInfo = new MatchInfo();
    matchInfo.setUser_1(user_id);
    matchInfo.setUser_2(id);
    matchInfo.setis_active(true);
    matchInfoMapper.insertMatchInfo(matchInfo);
    // cpu_idを追加する。
    model.addAttribute("cpu_id", id);
    model.addAttribute("user_name", prin.getName());
    model.addAttribute("cpu_name", userMapper.selectAllUsers().get(id - 1).getName());
    return "match.html";
  }
}
