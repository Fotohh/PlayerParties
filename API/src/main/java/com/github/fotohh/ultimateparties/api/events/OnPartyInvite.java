/**
 * The package com.github.fotohh.ultimateparties.api.events contains custom event classes related to the Party system in the UltimateParties API.
 * Custom events allow plugins to respond to specific occurrences in the party functionality.
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api.events;

import com.github.fotohh.ultimateparties.api.party.PartyInvite;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * The OnPartyInvite event is triggered when a player receives a party invitation in the UltimateParties API.
 * Plugins can listen to this event to respond to party invitations and perform custom actions.
 *
 * <p>The event provides the following information:</p>
 * <ul>
 *     <li>{@link #getPartyInvite()} - Gets the party invitation that was received.</li>
 * </ul>
 *
 * <p>To handle this event, register a listener and implement the corresponding event handler method in your plugin.</p>
 *
 * <pre>{@code
 * // Example of handling the OnPartyInvite event
 * public class MyPartyInviteListener implements Listener {
 *
 *     // Event handler method
 *     @EventHandler
 *     public void onPartyInvite(OnPartyInvite event) {
 *         // Your custom logic here
 *         PartyInvite invite = event.getPartyInvite();
 *         // Perform actions based on the received party invitation
 *     }
 * }
 * }</pre>
 *
 * @since 0.0.1
 */
public class OnPartyInvite extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final PartyInvite partyInvite;

    /**
     * Constructs an OnPartyInvite event with the party invitation that was received.
     *
     * @param invite The party invitation that was received.
     * @since 0.0.1
     */
    public OnPartyInvite(PartyInvite invite) {
        this.partyInvite = invite;
    }

    /**
     * Gets the party invitation that was received.
     *
     * @return The party invitation that was received.
     * @since 0.0.1
     */
    public PartyInvite getPartyInvite() {
        return partyInvite;
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
        return handlers;
    }

    /**
     * Gets the list of event handlers for this event.
     * This method is required for custom events.
     *
     * @return The list of event handlers for this event.
     * @since 0.0.1
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
