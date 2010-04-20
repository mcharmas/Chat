/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.actions;

import hearts.defs.state.GameState;
import hearts.defs.state.GameStateException;
import java.io.Serializable;

/**
 *
 * @author szymon
 */
public abstract class Action  implements Serializable, Cloneable {

    /**
     * Pole musi być ustawione podczas tworzenia obiektu
     */
    protected int receiver;

    /**
     * To pole musi być ustawione podczas odbierania obiektu z socketu
     * na bazie przynależności tego socketu.
     */
    protected int sender;

    public Action(int receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Metoda używana wywoływana przez użytkownika klasy.
     * Wywołuje performOnCopy() z klonem stanu gry.
     * @param old stary stan gry
     * @return nowy stan gry po przeleceniu
     */
    public final GameState perform(GameState old) throws GameStateException {
        return performOnCopy(old.clone());
    }

    /**
     * Metoda do zaimplementowania logiki akcji, dostaje kopię stanu gry
     * czyli bezpieczenie moze robić z nim co chce,
     * @param copy
     * @return zmodyfikowana kopia stanu gry przez tę akcję
     * @throws GameStateException
     */
    protected abstract GameState performOnCopy(GameState copy) throws GameStateException;

    public int getReceiver() {
        return receiver;
    }

    public int getSender() {
        return sender;
    }
}
