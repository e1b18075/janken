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
      hand = "Gu";
    } else if (player == 1) {
      hand = "Choki";
    } else if (player == 2) {
      hand = "Pa";
    }
    return hand;
  }

  public String cpuhand() {
    String hand = "";
    if (cpu == 0) {
      hand = "Gu";
    } else if (cpu == 1) {
      hand = "Choki";
    } else if (cpu == 2) {
      hand = "Pa";
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
