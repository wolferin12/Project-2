import java.util.ArrayList;
import java.util.Random;

public class CardGame {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public CardGame() {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void fillDeck(int totalPokemon) {
        deck.clear();
        for (int i = 0; i < 60 - totalPokemon; i++) {
            deck.add(new Energy());
        }
        for (int i = 0; i < totalPokemon; i++) {
            deck.add(new Pikachu()); // Adding Pokémon cards
        }
    }

    public void drawHand() {
        hand.clear();
        Random rng = new Random();
        for (int i = 0; i < 7; i++) {
            int cardToTakeIndex = rng.nextInt(deck.size());
            hand.add(deck.get(cardToTakeIndex));
            deck.remove(cardToTakeIndex);
        }
    }

    public boolean cardChecker() {
        for (Card singleCard : hand) {
            if (singleCard instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }

    public int reshuffleUntilPokemon() {
        int reshuffles = 0;
        while (!cardChecker()) {
            deck.addAll(hand); // Return hand to deck
            drawHand();
            reshuffles++;
        }
        return reshuffles;
    }

    public double calculateProbability(int totalPokemon, int trials) {
        int successCount = 0;

        for (int i = 0; i < trials; i++) {
            fillDeck(totalPokemon);
            drawHand();
            if (cardChecker()) {
                successCount++;
            }
        }

        return (double) successCount / trials;
    }

    public void run() {
        int trials = 10000; // Number of simulations
        System.out.println("Pokemon Count, Probability of Drawing At Least One in Hand");
        for (int totalPokemon = 1; totalPokemon <= 10; totalPokemon++) {
            double probability = calculateProbability(totalPokemon, trials);
            System.out.printf("%d, %.4f%n", totalPokemon, probability);
        }
    }

    public static void main(String[] args) {
        CardGame game = new CardGame();
        game.run();
    }
}

