package game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;

public class GameObjectFactory {

	private List<Card> cardsList;
	private List<Card> cardsQueue;
	private Card genericCard;
	private boolean initialized = false;

	public GameObjectFactory() {
	}

	public void initialize() {
		if (!initialized) {
			genericCard = new FuelTheFire();
			cardsList = new ArrayList<Card>();
			cardsList.add(genericCard);
			cardsQueue = new ArrayList<Card>();
			cardsQueue.add(genericCard);
			initialized = true;
		}
	}

	public Hand getHandObject() {
		initialize();
		return new Hand(cardsList);
	}

	public Deck getDeckObject() {
		initialize();
		return new Deck(cardsQueue);
	}

	public Card getCardObject() {
		initialize();
		return genericCard;
	}
}
