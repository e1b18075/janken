package oit.is.z1439.kaizi.janken.model;

public class Janken {
  int player;
  int cpu;

  public Janken(int player) {
    this.player = player;
    this.cpu = 0;
  }

  public String playerhand() {
    String hand = "";
    if (player == 0) {
      hand = "グー";
    } else if (player == 1) {
      hand = "チョキ";
    } else if (player == 2) {
      hand = "パー";
    }
    return hand;
  }

  public String cpuhand() {
    String hand = "";
    if (cpu == 0) {
      hand = "グー";
    } else if (cpu == 1) {
      hand = "チョキ";
    } else if (cpu == 2) {
      hand = "パー";
    }
    return hand;
  }

  public String score() {
    String score;
    if (player == 0 && cpu == 1 || player == 1 && cpu == 2 || player == 2 && cpu == 0) {
      score = "You Win!!";
    } else if (player == 0 && cpu == 2 || player == 1 && cpu == 0 || player == 2 && cpu == 1) {
      score = "You Lose!!";
    } else {
      score = "You Draw";
    }
    return score;
  }
}
