package oit.is.z1439.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1439.kaizi.janken.model.Janken;

@Controller
public class Lec02Controller {

  @GetMapping("/lec02")
  public String lec02() {
    return "lec02.html";
  }

  @GetMapping("/lec02_geam")
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
