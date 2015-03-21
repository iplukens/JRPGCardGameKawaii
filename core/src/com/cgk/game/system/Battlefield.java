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
    private String musicFileLocation;

    public Deck getDeck() {
        return deck;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Hero> getHeroes() {
        return hero;
    }

    public String getMusicFileLocation() {
        return musicFileLocation;
    }
    
    public void setMusicFileLocation(String fileLocation){
        musicFileLocation = fileLocation;
    }
}
