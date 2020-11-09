package oit.is.z1439.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1439.kaizi.janken.model.Janken;
import oit.is.z1439.kaizi.janken.model.Entry;
import oit.is.z1439.kaizi.janken.model.User;
import oit.is.z1439.kaizi.janken.model.UserMapper;
import oit.is.z1439.kaizi.janken.model.Match;
import oit.is.z1439.kaizi.janken.model.MatchMapper;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @GetMapping("/lec02")
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("matches", matchMapper.selectAllMatches());
    model.addAttribute("users", userMapper.selectAllUsers());
    return "lec02.html";
  }

  @GetMapping("/lec02/geam")
  public String lec02_geam(@RequestParam Integer jankenhand, ModelMap model) {

    Janken hand = new Janken(jankenhand);
    model.addAttribute("my_hand", hand.playerhand());
    model.addAttribute("your_hand", hand.cpuhand());
    model.addAttribute("Result", hand.score());
    return "lec02.html";
  }

  // matchのGetMapping
  @GetMapping("/match")
  public String match(@RequestParam Integer id, Principal prin, ModelMap model) {
    model.addAttribute("user_name", prin.getName());
    model.addAttribute("cpu_name", userMapper.selectAllUsers().get(id).getName());
    return "match.html";
  }
}
