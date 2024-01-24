package com.backlight.leaderbod.repositries;

import com.backlight.leaderbod.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepo extends JpaRepository<Player,String> {

    @Query("SELECT p FROM Player  p " +
            "WHERE YEARWEEK(p.TimeStamp) = YEARWEEK(CURRENT_DATE) " +
            "ORDER BY p.Score DESC " +
            "LIMIT 200")
    List<Player> getCurrentWeekLeaderboard();
//
    @Query("SELECT p FROM Player p " +
            "WHERE WEEK(p.TimeStamp) = WEEK(CURRENT_TIMESTAMP)-1" +
            "AND p.Country = :country " +
            "ORDER BY p.Score DESC " +
            "LIMIT 200")
    List<Player> getLastWeekLeaderboardByCountry(@Param("country") String country);
//
    @Query("SELECT COUNT(p) + 1 FROM Player p " +
            "WHERE p.Score > (SELECT p2.Score FROM Player p2 WHERE p2.UID = :userId)")
    int getUserRank(@Param("userId") String userId);

}
