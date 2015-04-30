package game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.system.EventQueue;

public class GameObjectFactory {

	private EventQueue eventQueue;
	private List<Card> cardsList;
	private List<Card> cardsQueue;
	private Card genericCard;
	private boolean initialized = false;

	public GameObjectFactory(EventQueue eventQueue) {
		this.eventQueue = eventQueue;
	}

	public void initialize() {
		if (!initialized) {
			genericCard = new FuelTheFire(eventQueue);
			cardsList = new ArrayList<Card>();
			cardsList.add(genericCard);
			cardsQueue = new ArrayList<Card>();
			cardsQueue.add(genericCard);
			initialized = true;
		}
	}

	public Hand getHandObject() {
		initialize();
		return new Hand(eventQueue, cardsList);
	}

	public Deck getDeckObject() {
		initialize();
		return new Deck(eventQueue, cardsQueue);
	}

	public Card getCardObject() {
		initialize();
		return genericCard;
	}
}
