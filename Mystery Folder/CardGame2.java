import java.util.ArrayList;
import java.util.Random;

public class CardGame2 {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public CardGame2() {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void fillDeck() {
        // Add 15 Energy cards
        for (int i = 0; i < 15; i++) {
            deck.add(new Energy());
        }
        // Add Pokémon cards (3-5)
        deck.add(new Charmander());
        deck.add(new MagiKarp());
        deck.add(new Pikachu());
        deck.add(new Mimikyu());
        deck.add(new Ditto());

        // Add Trainer cards
        for (int i = 0; i < 10; i++) {
            deck.add(new TrainerCard("Potion"));
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new TrainerCard("Revive"));
        }
    }

    public void drawHand() {
        Random rng = new Random();
        hand.clear();
        for (int i = 0; i < 7; i++) {
            int cardToTakeIndex = rng.nextInt(deck.size());
            hand.add(deck.get(cardToTakeIndex));
            deck.remove(cardToTakeIndex);
        }
        System.out.println("Hand drawn:");
        for (Card card : hand) {
            System.out.println(card.toString());}
    }

    public boolean cardChecker() {
        for (Card singleCard : hand) {
            if (singleCard instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }

    public void reshuffleCards() {
        while (!cardChecker()) {
            System.out.println("No Pokémon found. Redrawing hand...");
            deck.addAll(hand);
            hand.clear();
            drawHand();
        }
    }

    public void run() {
        fillDeck();
        drawHand();
        reshuffleCards();
        System.out.println("A valid hand has been drawn!");
    }

    public static void main(String[] args) {
        CardGame2 game = new CardGame2();
        game.run();
    }
}
