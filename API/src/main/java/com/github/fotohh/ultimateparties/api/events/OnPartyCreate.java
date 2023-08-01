/**
 * The package com.github.fotohh.ultimateparties.api.events contains custom event classes related to the Party system in the UltimateParties API.
 * Custom events allow plugins to respond to specific occurrences in the party functionality.
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api.events;

import com.github.fotohh.ultimateparties.api.party.Party;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * The OnPartyCreate event is triggered when a new party is created in the UltimateParties API.
 * Plugins can listen to this event to respond to the creation of parties and perform custom actions.
 *
 * <p>The event provides the following information:</p>
 * <ul>
 *     <li>{@link #getParty()} - Gets the newly created party.</li>
 *     <li>{@link #getPlayer()} - Gets the player who created the party.</li>
 * </ul>
 *
 * <p>To handle this event, register a listener and implement the corresponding event handler method in your plugin.</p>
 *
 * <pre>{@code
 * // Example of handling the OnPartyCreate event
 * public class MyPartyCreateListener implements Listener {
 *
 *     // Event handler method
 *     @EventHandler
 *     public void onPartyCreate(OnPartyCreate event) {
 *         // Your custom logic here
 *         Party party = event.getParty();
 *         Player player = event.getPlayer();
 *         // Perform actions based on the newly created party and the player who created it
 *     }
 * }
 * }</pre>
 *
 * @since 0.0.1
 */
public class OnPartyCreate extends Event {

    private final HandlerList handlerList = new HandlerList();

    private final Party party;
    private final Player player;

    /**
     * Constructs an OnPartyCreate event with the newly created party and the player who created it.
     *
     * @param party  The newly created party.
     * @param player The player who created the party.
     * @since 0.0.1
     */
    public OnPartyCreate(Party party, Player player) {
        this.party = party;
        this.player = player;
    }

    /**
     * Gets the player who created the party.
     *
     * @return The player who created the party.
     * @since 0.0.1
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the newly created party.
     *
     * @return The newly created party.
     * @since 0.0.1
     */
    public Party getParty() {
        return party;
    }

    /**
     * Gets the list of event handlers for this event.
     * This method is required for custom events.
     *
     * @return The list of event handlers for this event.
     * @since 0.0.1
     */
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
