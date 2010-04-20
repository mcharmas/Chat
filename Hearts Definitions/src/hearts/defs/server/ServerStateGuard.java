package hearts.defs.server;

import hearts.defs.actions.ActionListener;
import hearts.defs.state.GameState;
import hearts.defs.state.GameStateException;

/**
 * Klasa opiekująca się stanem gry, odpowiedzialna za:
 * <ol>
 * <li>przyporządkowaniu userom id na czas rozgrywki od 0 do 3.</li>
 * <li>rozpoczęcie gry jesli liczba userów == 4</li>
 * <li>trzymanie obiektu stanu gry</li>
 * <li>nasłuchiwanie na UserSocketach, tzn. dodaje siebie jako ich Listener</li>
 * <li>przetwarzanie otrzymanych akcji przy pomocy Judge'a</li>
 * <li>każdorazowo po przetworzeniu stanu gry sprawdza listę
 * obiektów Action do rozesłania i rozsyła do adresatów albo ID usera albo
 * GameConstants.ALL_USER</li>
 * </ol>
 * @author szymon
 */
public interface ServerStateGuard extends ActionListener, Runnable {
    /**
     * Dodaje usera do tablicy
     * @param socket
     * @throws GameStateException
     * @return id usera 0...3
     */
    public int addUser(UserSocket socket) throws GameStateException;

    public GameState getState();
}
