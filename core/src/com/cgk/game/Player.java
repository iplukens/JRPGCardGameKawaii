package com.cgk.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String playerId;
	private HeroCharacter activeCharacter;
	private List<HeroCharacter> characters;

	public Player() {
		characters = new ArrayList<>();
	}

	public Player(String playerId, List<HeroCharacter> characters) {
		this.setPlayerId(playerId);
		this.characters = characters;
	}

	public List<HeroCharacter> getCharacters() {
		return characters;
	}

	public boolean hasCharacters() {
		return !characters.isEmpty();
	}

	public void addCharacter(HeroCharacter hero) {
		characters.add(hero);
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public HeroCharacter getActiveCharacter() {
		return activeCharacter;
	}

	public void setActiveCharacter(HeroCharacter activeCharacter) {
		this.activeCharacter = activeCharacter;
	}

}
