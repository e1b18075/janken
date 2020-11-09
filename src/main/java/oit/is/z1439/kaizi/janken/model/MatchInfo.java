package oit.is.z1439.kaizi.janken.model;

public class MatchInfo {

  int id;
  int user_1;
  int user_2;
  boolean is_active;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser_1() {
    return this.user_1;
  }

  public void setUser_1(int user_1) {
    this.user_1 = user_1;
  }

  public int getUser_2() {
    return this.user_2;
  }

  public void setUser_2(int user_2) {
    this.user_2 = user_2;
  }

  public boolean getis_active() {
    return this.is_active;
  }

  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

}
