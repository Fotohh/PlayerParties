/**
 * The package com.github.fotohh.ultimateparties.api.player contains interfaces related to player interactions in the Party system of the UltimateParties API.
 * The PartyPlayer interface provides methods for managing a player's party-related actions and information.
 *
 * @since 0.0.1
 */
package com.github.fotohh.ultimateparties.api.player;

import com.github.fotohh.ultimateparties.api.party.Party;
import com.github.fotohh.ultimateparties.api.party.PartyInvite;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The PartyPlayer interface represents a player and provides methods for managing a player's party-related actions and information within the UltimateParties API.
 * A PartyPlayer can be associated with a party (if the player is a member of a party) or have pending party invitations.
 *
 * <p>The interface provides methods to:</p>
 * <ul>
 *     <li>Set and get the party the player is currently a member of ({@link #setParty(Party)} and {@link #getParty()}).</li>
 *     <li>Get the UUID of the player ({@link #getUUID()}).</li>
 *     <li>Check if the player is currently a member of a party ({@link #isInParty()}).</li>
 *     <li>Accept a party invitation ({@link #acceptInvite(PartyInvite)}).</li>
 *     <li>Add a party invitation to the player's pending invites list ({@link #addPartyInvite(PartyInvite)}).</li>
 *     <li>Remove a party invitation from the player's pending invites list ({@link #removePartyInvite(PartyInvite)}).</li>
 *     <li>Get the list of party invitations the player has received ({@link #getPartyInvites()}).</li>
 * </ul>
 *
 * @since 0.0.1
 */
public interface PartyPlayer {

    /**
     * Sets the party the player is currently a member of.
     * This method is used to associate a player with a party when they join or leave a party.
     *
     * @param party The party the player is joining or leaving.
     * @since 0.0.1
     */
    void setParty(Party party);

    /**
     * Gets the UUID of the player.
     *
     * @return The UUID of the player.
     * @since 0.0.1
     */
    UUID getUUID();

    /**
     * Gets the party the player is currently a member of.
     * If the player is not currently in any party, this method will return {@code null}.
     *
     * @return The party the player is a member of, or {@code null} if the player is not in any party.
     * @since 0.0.1
     */
    Party getParty();

    /**
     * Checks if the player is currently a member of a party.
     *
     * @return {@code true} if the player is in a party, {@code false} otherwise.
     * @since 0.0.1
     */
    boolean isInParty();

    /**
     * Accepts a party invitation.
     * This method is called when the player accepts an invitation to join a party.
     *
     * @param invite The party invitation to accept.
     * @since 0.0.1
     */
    void acceptInvite(PartyInvite invite);

    /**
     * Adds a party invitation to the player's list of pending invites.
     * This method is used to store invitations received by the player until they decide to accept or decline them.
     *
     * @param invite The party invitation to add to the player's pending invites.
     * @since 0.0.1
     */
    void addPartyInvite(PartyInvite invite);

    /**
     * Removes a party invitation from the player's list of pending invites.
     * This method is used when the player accepts or declines an invitation, or the invitation expires.
     *
     * @param invite The party invitation to remove from the player's pending invites.
     * @since 0.0.1
     */
    void removePartyInvite(PartyInvite invite);

    /**
     * Gets the list of party invitations the player has received.
     *
     * @return The list of party invitations received by the player.
     * @since 0.0.1
     */
    ArrayList<PartyInvite> getPartyInvites();
}
