package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;

import static domain.result.ResultGamersScore.BLACK_JACK_NUMBER;

public class Gamer {

    private final int ACE_RECOVER_NUMBER = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return "";
    }

    public int getCardSize() {
        return cards.size();
    }

    public int getScoreOfGamer() {
        int scoreOfGamer = getCardScore();
        for (int i = 0; i < getAceCount(); i++) {
            scoreOfGamer = recoverAceNumber(scoreOfGamer);
        }
        return scoreOfGamer;
    }

    private int getCardScore() {
        return cards.stream()
                .map(Card::getSymbol)
                .mapToInt(Symbol::getScore)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(c -> c.getSymbol() == Symbol.ACE)
                .count();
    }

    private int recoverAceNumber(int scoreOfGamer) {
        if (BLACK_JACK_NUMBER < scoreOfGamer) {
            scoreOfGamer -= ACE_RECOVER_NUMBER;
        }
        return scoreOfGamer;
    }

}
