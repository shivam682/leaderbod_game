package com.backlight.leaderbod.controller;

import com.backlight.leaderbod.payloads.ApiResponse;
import com.backlight.leaderbod.payloads.PlayerDto;
import com.backlight.leaderbod.payloads.RankDto;
import com.backlight.leaderbod.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/player")
    private ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto){
        PlayerDto createdPlayer = this.playerService.createPlayer(playerDto);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }
    @PutMapping("/player/{playerId}")
    private ResponseEntity<PlayerDto> updatePlayerById(@PathVariable String playerId, @RequestBody PlayerDto playerDto){
        PlayerDto updatedPlayer = this.playerService.updatePlayer(playerId,playerDto);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @GetMapping("/player/{playerId}")
    private ResponseEntity<PlayerDto> getPlayerById(@PathVariable String playerId){
        PlayerDto player = this.playerService.getPlayerById(playerId);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
    @DeleteMapping("/player/{playerId}")
    private ResponseEntity<ApiResponse> deletePlayerById(@PathVariable String playerId){
        this.playerService.deletePlayer(playerId);
        return new ResponseEntity<>(new ApiResponse("Player deleted",true), HttpStatus.OK);
    }
    @GetMapping("/player/")
    private ResponseEntity<List<PlayerDto>> getCurrentWeekLeaderboard(){
        List<PlayerDto> playerDtoList = this.playerService.getCurrentWeekLeaderboard();
        return new ResponseEntity<>(playerDtoList,HttpStatus.OK);
    }
    @GetMapping("/player/country/{countryCode}")
    private ResponseEntity<List<PlayerDto>> getLastWeekLeaderboardByCountry(@PathVariable String countryCode){
        List<PlayerDto> playerDtoList = this.playerService.getLastWeekLeaderboardByCountry(countryCode);
        return new ResponseEntity<>(playerDtoList,HttpStatus.OK);
    }
//
//
    @GetMapping("/player/rank/{playerId}")
    private ResponseEntity<RankDto> getRankByPlayerId(@PathVariable String playerId){
        RankDto rankDto = this.playerService.getRankByPlayerId(playerId);
        return new ResponseEntity<>(rankDto,HttpStatus.OK);
    }

}
