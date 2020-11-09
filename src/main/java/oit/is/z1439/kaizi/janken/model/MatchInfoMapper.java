package oit.is.z1439.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {
  @Select("SELECT * from match_info")
  ArrayList<MatchInfo> selectAllMatchInfo();

  @Insert("INSERT INTO matches (user_1,user_2,,is_active) VALUES (#{user_1},#{user_2},#{is_active});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo matchInfo);
}
