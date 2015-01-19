package com.cgk.game.gameobject;

import java.util.List;

import com.cgk.game.event.EventQueue;
import com.cgk.game.gameobject.enemy.Enemy;

public class Battlefield {

	private List<Enemy> enemies;
	private List<Hero> hero;
	private Deck deck;
	private Hand hand;
	private EventQueue events;
}
