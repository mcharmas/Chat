package hearts.defs.judge;

import hearts.defs.state.GameState;
import hearts.defs.state.GameStateException;
import hearts.defs.actions.Action;

/**
 * Klasa
 * @author szymon
 */
public interface Judge {
    /**
     * <p>Dostaje stan gry i akcję, na podstawie typu akcji ocenia, czy akcja
     * jest w tym momencie dozwolona i wywołuje na akcji perform();</p>
     *
     * <p>Potem sam podejmuje ew. akcje i dodaje swoje komunikaty do kolejki
     * w stanie gry przez state.addAction();</p>
     *
     * <p>Specjalną troską powinien opatrzyć akcje dziedziczące po ChatAction:
     * podawać jej odrębny stan gry, jakiś DumbState implementujący tylko
     * kolejkę akcji do rozesłania.</p>
     *
     * @param state aktualny stan gry
     * @param action akcja do rozpatrzenia
     * @return nowy stan gry
     * @throws GameStateException jeśli akcja nie jest dozwolona albo nie wie
     * co z nią zrobić.
     */
    public GameState judge(GameState state, Action action) throws GameStateException;
}
