package com.padeltrophy.controller;

import com.padeltrophy.entity.Player;
import com.padeltrophy.repository.PlayerRestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SuscribeController {

	@Autowired
	PlayerRestRepository playerRestRepository;

	/**
	 * Suscribe a player
	 * @param player
	 */
	@RequestMapping(value="/suscribe",method= RequestMethod.POST,consumes = "application/json",produces = "application/json")
	public ResponseEntity<Player> getAvailability(@RequestBody Player player) {
		Player response = playerRestRepository.save(player);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}