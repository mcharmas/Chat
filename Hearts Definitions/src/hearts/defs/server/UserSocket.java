package hearts.defs.server;

import hearts.defs.actions.ActionListener;
import hearts.defs.actions.ActionNotifier;

/**
 * Implementacja powinna trzymać socket z klientem i powiadamiać
 * ActionListenerów o tym co przyszło na ten socket
 *
 * Jest także ActionListenerem, popychającym obiekty Action do klienta.
 * @author szymon
 */
public interface UserSocket extends ActionNotifier, ActionListener {
    public String getName();
}
