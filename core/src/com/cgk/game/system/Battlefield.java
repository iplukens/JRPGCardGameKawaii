package com.cgk.game.system;

import java.util.List;

import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.hero.Hero;

public class Battlefield {

	private List<Enemy> enemies;
	private List<Hero> hero;
	private Deck deck;
	private Hand hand;
	private EventQueue events;
        
        public Deck getDeck(){
            return deck;
        }
        
        public List<Enemy> getEnemies(){
            return enemies;
        }
        
        public List<Hero> getHeroes(){
            return hero;
        }

    public String getMusic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
