package oit.is.z1439.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1439.kaizi.janken.model.Janken;
import oit.is.z1439.kaizi.janken.model.Entry;
import oit.is.z1439.kaizi.janken.model.User;
import oit.is.z1439.kaizi.janken.model.UserMapper;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/lec02")
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("login_user", loginUser);
    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);
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

  @PostMapping("/lec02")
  public String lec02(@RequestParam String username, ModelMap model) {
    model.addAttribute("username", username);
    return "lec02.html";
  }
}
