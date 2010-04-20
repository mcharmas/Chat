/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author szymon
 */
public interface UserState extends Serializable, Cloneable {

    /**
     * Dodaje kartę do kart
     * @param c
     */
    public void addCard(Card c);

    public void removeCard(Card c);

    public String getName();

    public int getId();

    public void addTrick();

    /**
     * Pobierz
     * @return
     */
    public List<Trick> getTricks();

    public void clearTricks();

    /**
     * Zwraca listę punktów użytkownika od początku gry
     * @return
     */
    public List<Integer> getPointsList();

    /**
     * Dodaj punkty graczowi
     * @param points
     */
    public void addPoints(int points);
}
