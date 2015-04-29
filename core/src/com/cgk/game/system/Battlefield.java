package com.cgk.game.system;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.hero.Hero;

public class Battlefield {

	private List<Enemy> enemies;
	private List<Hero> heroes;
	private Deck deck;
	private Hand hand;
	private String musicFileLocation;
	private GameState gameState;

	// TODO: support multiple levels

	/**
	 * 
	 * @param playerAssets
	 * @param levelId
	 */
	public Battlefield(PlayerAssets playerAssets, int levelId) {
		EventQueue queue = new EventQueue();
		deck = playerAssets.getDeck(queue);
		hand = new Hand(queue);
		LevelAssets levelAssets = new LevelAssets(levelId);
		enemies = levelAssets.getEnemies(queue, 1);
		heroes = playerAssets.getHeroes(queue);
		gameState = GameState.PLAYER_TURN;
	}

	public Deck getDeck() {
		return deck;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Hero> getHeroes() {
		return heroes;
	}

	public String getMusicFileLocation() {
		return musicFileLocation;
	}

	public void setMusicFileLocation(String fileLocation) {
		musicFileLocation = fileLocation;
	}

	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		deck.draw(batcher, atlas);
		hand.draw(batcher, atlas);
		for (Enemy enemy : enemies) {
			enemy.draw(batcher, atlas);
		}
		for (Hero hero : heroes) {
			hero.draw(batcher, atlas);
		}
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Hand getHand() {
		return hand;
	}
}
