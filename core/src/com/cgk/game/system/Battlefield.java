package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.HeroCharacter;
import com.cgk.game.event.EndEnemyTurnEvent;
import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.hero.Hero;

public class Battlefield {

	private int currentFloor;
	private Enemy currentTarget;
	private List<Enemy> enemies;
	private List<Hero> heroes;
	private Deck deck;
	private Hand hand;
	private ComboTracker comboTracker;
	private String musicFileLocation;
	private GameState gameState;
	private PlayerTurnTimer timer;
	private LevelAssets levelAssets;

	// TODO: support multiple levels

	public Battlefield() {
		enemies = new ArrayList<>();
		heroes = new ArrayList<>();
		levelAssets = new LevelAssets(0);
	}

	/**
	 * 
	 * @param heroCharacter
	 * @param levelId
	 */
	public Battlefield(HeroCharacter heroCharacter, int levelId) {
		musicFileLocation = "assets/song.mp3";
		currentFloor = 1;
		EventQueue queue = new EventQueue();
		GameObject.setQueue(queue);
		GameObject.setBattlefield(this);
		timer = heroCharacter.getTimer();
		hand = new Hand();
		deck = heroCharacter.getDeck();
		comboTracker = heroCharacter.getComboTracker();
		levelAssets = new LevelAssets(levelId);
		enemies = new ArrayList<>();
		enemies.addAll(levelAssets.getEnemies(currentFloor));
		heroes = new ArrayList<>();
		heroes.addAll(heroCharacter.getHeroes());
		gameState = GameState.BETWEEN_TURNS;
		for (int i = 0; i < heroCharacter.getMaxHandSize(); i++) {
			deck.drawCard();
		}
		timer.startTimer();
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

	public ComboTracker getComboTracker() {
		return comboTracker;
	}

	public String getMusicFileLocation() {
		return musicFileLocation;
	}

	public void setMusicFileLocation(String fileLocation) {
		musicFileLocation = fileLocation;
	}

	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		timer.draw(batcher, atlas);
		deck.draw(batcher, atlas);
		for (Enemy enemy : enemies) {
			enemy.draw(batcher, atlas);
		}
		for (Hero hero : heroes) {
			hero.draw(batcher, atlas);
		}
		comboTracker.draw(batcher, atlas);
		if (gameState == GameState.PLAYER_TURN) {
			hand.draw(batcher, atlas);
		} else {
			hand.drawBacks(batcher, atlas);
		}
		if (currentTarget != null) {
			currentTarget.drawTarget(batcher, atlas);
		}
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		if (this.gameState != GameState.DEFEAT
				&& this.gameState != GameState.VICTORY) {
			this.gameState = gameState;
		}
	}

	public Hand getHand() {
		return hand;
	}

	public void updateTimer(float delta) {
		timer.update(delta);
		if (turnOver()) {
			setGameState(GameState.ENEMY_TURN);
			hand.resetTouches(null);
		}
	}

	public boolean turnOver() {
		return timer.turnOver();
	}

	public void enemyAttackProcess() {
		GameObject.getQueue().put(new EndEnemyTurnEvent());
		setGameState(GameState.BETWEEN_TURNS);
	}

	public void startPlayerTurn() {
		comboTracker.reset();
		timer.startTimer();
		setGameState(GameState.PLAYER_TURN);
	}

	public void addHero(Hero hero) {
		heroes.add(hero);
	}

	public void removeHero(Hero hero) {
		heroes.remove(hero);
		if (heroes.isEmpty()) {
			setGameState(GameState.DEFEAT);
		}
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
		if (currentTarget != null && currentTarget.equals(enemy)) {
			currentTarget = null;
		}
		if (enemies.isEmpty()) {
			advanceLevel();
		}
	}

	private void advanceLevel() {
		if (levelAssets.hasLevel(currentFloor + 1)) {
			enemies = levelAssets.getEnemies(++currentFloor);
		} else {
			setGameState(GameState.VICTORY);
		}
	}

	public List<GameObject> getObjectsToLoad() {
		List<GameObject> objectsToLoad = new ArrayList<>();
		objectsToLoad.add(getDeck());
		objectsToLoad.addAll(getHeroes());
		objectsToLoad.addAll(getEnemies());
		objectsToLoad.addAll(getDeck().getCards());
		objectsToLoad.add(getHand());
		objectsToLoad.addAll(getHand().getCards());
		objectsToLoad.add(getComboTracker());
		return objectsToLoad;
	}

	public Enemy getCurrentTarget() {
		if (enemies.size() == 0) {
			System.out
					.println("THERE ARE NO ENEMIES. WHAT ARE YOU TRYING TO ATTACK?");
			return null;
		}
		if (currentTarget != null) {
			return currentTarget;
		} else {
			return enemies.get(0);
		}
	}

	public void setCurrentTarget(Enemy currentTarget) {
		this.currentTarget = currentTarget;
	}

	public boolean processTarget(Vector2 touchPos) {
		for (Enemy enemy : enemies) {
			if (enemy.touched(touchPos)) {
				currentTarget = enemy;
				return true;
			}
		}
		return false;
	}

	public Hero getEnemyTarget() {
		// TODO: complete the enemy target selecting algorithm
		if (heroes.isEmpty()) {
			return null;
		}
		return heroes.get(0);
	}
}
